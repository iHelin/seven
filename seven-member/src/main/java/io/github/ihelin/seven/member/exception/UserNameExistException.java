package io.github.ihelin.seven.member.exception;

public class UserNameExistException extends RuntimeException {
	public UserNameExistException() {
		super("用户名存在");
	}
}
