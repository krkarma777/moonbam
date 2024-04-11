package com.moonBam.springSecurity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.moonBam.dto.MemberDTO;


public class SpringSecurityUser implements UserDetails{

	private MemberDTO dto;
	
	public SpringSecurityUser(MemberDTO dto) {
		this.dto = dto;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> col = new ArrayList<>();
		
		col.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return dto.getRole();
			}
		});
		
		return col;
	}

	@Override
	public String getPassword() {
		return dto.getUserPw();
	}

	@Override
	public String getUsername() {
		return dto.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return dto.isEnabled();
	}

}