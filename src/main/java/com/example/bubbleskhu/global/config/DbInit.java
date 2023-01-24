package com.example.bubbleskhu.global.config;

import com.example.bubbleskhu.global.common.security.dao.PrivilegeRepository;
import com.example.bubbleskhu.global.common.security.dao.RoleRepository;
import com.example.bubbleskhu.global.common.security.role.Privilege;
import com.example.bubbleskhu.global.common.security.role.Role;
import com.example.bubbleskhu.major.dao.MajorRepository;
import com.example.bubbleskhu.major.domain.Major;
import com.example.bubbleskhu.member.dao.UserRepository;
import com.example.bubbleskhu.member.domain.User;
import com.example.bubbleskhu.model.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Component
public class DbInit {
    @Autowired
    MajorRepository majorRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @PostConstruct
    public void init() {

        Privilege bubble_read = Privilege.builder().name("BUBBLE_READ").build();
        Privilege bubble_create = Privilege.builder().name("BUBBLE_CREATE").build();
        Privilege bubble_update = Privilege.builder().name("BUBBLE_UPDATE").build();
        Privilege bubble_delete = Privilege.builder().name("BUBBLE_DELETE").build();
        privilegeRepository.save(bubble_read);
        privilegeRepository.save(bubble_create);
        privilegeRepository.save(bubble_update);
        privilegeRepository.save(bubble_delete);

        Role role_anonymous = Role.builder().name("ROLE_ANONYMOUS").privileges(Collections.singletonList(bubble_read)).build();
        Role role_user = Role.builder().name("ROLE_USER").privileges(Arrays.asList(bubble_read, bubble_create, bubble_delete, bubble_update)).build();
        Role role_admin = Role.builder().name("ROLE_ADMIN").privileges(Arrays.asList(bubble_read, bubble_create, bubble_delete, bubble_update)).build();
        roleRepository.save(role_anonymous);
        roleRepository.save(role_user);
        roleRepository.save(role_admin);
        Major It = Major.builder().name("IT융합자율학부").build();
        Major Media = Major.builder().name("미디어컨텐츠융합자율학부").build();
        Major Society = Major.builder().name("사회융합자율학부").build();
        Major Humanities = Major.builder().name("인문융합자율학부").build();

        majorRepository.save(It);
        majorRepository.save(Media);
        majorRepository.save(Society);
        majorRepository.save(Humanities);

        User user = User.builder()
                .userId("admin")
                .userPassword(passwordEncoder.encode("admin"))
                .major(majorRepository.findById(1L).get())
                .grade(Grade.FIRST)
                .name("관리자")
                .roles(Collections.singletonList(roleRepository.findByName("ROLE_ADMIN")))
                .build();

        userRepository.save(user);



    }

}
