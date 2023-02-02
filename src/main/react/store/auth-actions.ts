import { GET, POST }  from "./fetch-auth-action";

const createTokenHeader = (grantType:string,token:string) => {
    return {
        headers: {
            'Authorization': grantType + token
        }
    }
}

const calculateRemainingTime = (expirationTime:number) => {
    const currentTime = new Date().getTime();
    const adjExpirationTime = new Date(expirationTime).getTime();
    const remainingDuration = adjExpirationTime - currentTime;
    return remainingDuration;
};

export const loginTokenHandler = (grantType:string, accessToken:string,refreshToken:string, refreshTokenExpirationTime:number) => {
    localStorage.setItem('grantType', grantType);
    localStorage.setItem('accessToken', accessToken);
    localStorage.setItem('refreshToken', refreshToken);
    localStorage.setItem('refreshTokenExpirationTime', String(refreshTokenExpirationTime));

    const remainingTime = calculateRemainingTime(refreshTokenExpirationTime);
    return remainingTime;
}

export const retrieveStoredToken = () => {
    const grantType = localStorage.getItem('grantType');
    const refreshToken = localStorage.getItem('refreshToken');
    const accessToken = localStorage.getItem('accessToken');
    const storedExpirationDate = localStorage.getItem('refreshTokenExpirationTime') || '0';

    const remaingTime = calculateRemainingTime(+ storedExpirationDate);

    if(remaingTime <= 1000) {
        localStorage.removeItem('token');
        localStorage.removeItem('expirationTime');
        return null
    }

    return {
        grantType : grantType,
        accessToken: accessToken,
        refreshToken : refreshToken,
        duration: remaingTime
    }
}

export const signupActionHandler = (name: string, majorId: number, grade: string, userId: string, userPassword: string) => {
    const URL = '/signup'
    const signupObject = {name, majorId, grade, userId, userPassword};

    const response = POST(URL, signupObject, {});
    return response;
};

export const loginActionHandler = (userId:string, userPassword: string) => {
    const URL = '/signin';
    const loginObject = { userId, userPassword};
    const response = POST(URL, loginObject, {});

    return response;
};

export const logoutActionHandler = (grantType:string,accessToken : string) => {
    localStorage.removeItem('token');
    localStorage.removeItem('refreshTokenExpirationTime');

    const URL = '/signout';
    const logoutObject = {accessToken};
    const response = POST(URL, logoutObject,  createTokenHeader(grantType,accessToken));

    return response;
};

export const getBubbles = (grantType:string,accessToken : string) => {
    const URL = '/bubbles';
    const response = GET(URL, createTokenHeader(grantType,accessToken));
    return response;
}
