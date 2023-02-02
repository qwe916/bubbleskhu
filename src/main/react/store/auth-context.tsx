import * as React from 'react';
import { useState, useEffect} from 'react';
import * as authAction from './auth-actions';

let logoutTimer: NodeJS.Timeout;

type Props = { children?: React.ReactNode }
type UserInfo = { email: string, nickname: string};
type LoginToken = {
    grantType: string,
    refreshToken: string,
    accessToken: string,
    tokenExpiresIn: number
}

const AuthContext = React.createContext({
    token: '',
    isLoggedIn: false,
    isSuccess: false,
    isGetSuccess: false,
    signup: (name: string, majorId:number,grade:string,userId: string, userPassword:string) =>  {},
    login: (userId:string, userPassword: string) => {},
    logout: (accessToken:string) => {},

});


export const AuthContextProvider:React.FC<Props> = (props) => {

    const tokenData = authAction.retrieveStoredToken();

    let initialAccessToken: string;
    let initialGrantType :string;
    let initialRefreshToken :string;
    let initialTokenExpiresIn :number;

    if (tokenData) {
        initialAccessToken = tokenData.accessToken!;
        initialGrantType = tokenData.grantType!;
        initialRefreshToken = tokenData.refreshToken!;
        initialTokenExpiresIn = tokenData.duration!;
    }



    const [accessToken, setAccessToken] = useState(initialAccessToken);
    const [grantType, setGrantType] = useState(initialGrantType);
    const [refreshToken, setRefreshToken] = useState(initialRefreshToken);
    const [tokenExpiresIn, setTokenExpiresIn] = useState(initialTokenExpiresIn);


    const [isSuccess, setIsSuccess] = useState<boolean>(false);
    const [isGetSuccess, setIsGetSuccess ] = useState<boolean>(false);

    const userIsLoggedIn = !!accessToken;



    const signupHandler = (name: string, majorId:number,grade:string,userId: string, userPassword:string) => {
        setIsSuccess(false);
        const response = authAction.signupActionHandler(name,majorId,grade,userId,userPassword);
        response.then((result) => {
            if (result !== null) {
                setIsSuccess(true);
            }
        });
    }

    const loginHandler = (userId:string, userPassword: string) => {
        setIsSuccess(false);
        console.log(isSuccess);

        const data = authAction.loginActionHandler(userId, userPassword);
        data.then((result) => {
            if (result !== null) {
                const loginData:LoginToken = result.data;
                setGrantType(loginData.grantType);
                setAccessToken(loginData.accessToken);
                setRefreshToken(loginData.refreshToken);
                setTokenExpiresIn(loginData.tokenExpiresIn);
                setIsSuccess(true);
                console.log(isSuccess);
            }
        })
    };

    const logoutHandler = () => {
        setIsGetSuccess(false);
        const data = authAction.logoutActionHandler(grantType,accessToken);
        data.then((result) => {
            if (result !== null) {
                setIsGetSuccess(true);
            }
        })
    };




    useEffect(() => {
        if(tokenData) {
            console.log(tokenData.duration);
            logoutTimer = setTimeout(logoutHandler, tokenData.duration);
        }
    }, [tokenData, logoutHandler]);


    const contextValue = {
        token: accessToken,
        isLoggedIn: userIsLoggedIn,
        isSuccess,
        isGetSuccess,
        signup: signupHandler,
        login: loginHandler,
        logout: logoutHandler,
    }

    return(
        <AuthContext.Provider value={contextValue}>
            {props.children}
        </AuthContext.Provider>
    )
}

export default AuthContext;