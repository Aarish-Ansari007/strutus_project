package com.indianmesh.inventory.login;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm {
	
	//---------------------------------------------------------------------------------------------------------------------
	//Properties
	//---------------------------------------------------------------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	private String userId=null;
	private String password=null;
	private String userFirstName=null;
	private String userMiddleName=null;
	private String userLastName=null;
	private String eMail=null;
	private String isValidUser=null;
	private String loginId=null;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	
	private String moduleCode=null;
	private String moduleName = null;
	private String submoduleName = null;
	private String submoduleCode = null;
	private String url = null;
	
	//The Member var is used for display the Pop For Error and  message 	
	private boolean informationDialog = false;
	private String informationDialogText = null;
	private String informationDialogHeader = null;
	
	//---------------------------------------------------------------------------------------------------------------------
	//Constructors Override 
	//---------------------------------------------------------------------------------------------------------------------
		
	public LoginForm() {
		super();
	}
		
	public LoginForm(String userId, String password, String userFirstName, String userMiddleName, String userLastName,
			String eMail, String isValidUser, String loginId, String oldPassword, String newPassword,
			String confirmPassword, String moduleCode, String moduleName, String submoduleName, String submoduleCode,
			String url, boolean informationDialog, String informationDialogText, String informationDialogHeader) {
		super();
		this.userId = userId;
		this.password = password;
		this.userFirstName = userFirstName;
		this.userMiddleName = userMiddleName;
		this.userLastName = userLastName;
		this.eMail = eMail;
		this.isValidUser = isValidUser;
		this.loginId = loginId;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
		this.moduleCode = moduleCode;
		this.moduleName = moduleName;
		this.submoduleName = submoduleName;
		this.submoduleCode = submoduleCode;
		this.url = url;
		this.informationDialog = informationDialog;
		this.informationDialogText = informationDialogText;
		this.informationDialogHeader = informationDialogHeader;
	}

	//---------------------------------------------------------------------------------------------------------------------
	//hashCode, equals and toString Method Override 
	//---------------------------------------------------------------------------------------------------------------------
		
	@Override
	public String toString() {
		return "LoginForm [userId=" + userId + ", password=" + password + ", userFirstName=" + userFirstName
				+ ", userMiddleName=" + userMiddleName + ", userLastName=" + userLastName + ", eMail=" + eMail
				+ ", isValidUser=" + isValidUser + ", loginId=" + loginId + ", oldPassword=" + oldPassword
				+ ", newPassword=" + newPassword + ", confirmPassword=" + confirmPassword + ", moduleCode=" + moduleCode
				+ ", moduleName=" + moduleName + ", submoduleName=" + submoduleName + ", submoduleCode=" + submoduleCode
				+ ", url=" + url + ", informationDialog=" + informationDialog + ", informationDialogText="
				+ informationDialogText + ", informationDialogHeader=" + informationDialogHeader + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		result = prime * result + (informationDialog ? 1231 : 1237);
		result = prime * result + ((informationDialogHeader == null) ? 0 : informationDialogHeader.hashCode());
		result = prime * result + ((informationDialogText == null) ? 0 : informationDialogText.hashCode());
		result = prime * result + ((isValidUser == null) ? 0 : isValidUser.hashCode());
		result = prime * result + ((loginId == null) ? 0 : loginId.hashCode());
		result = prime * result + ((moduleCode == null) ? 0 : moduleCode.hashCode());
		result = prime * result + ((moduleName == null) ? 0 : moduleName.hashCode());
		result = prime * result + ((newPassword == null) ? 0 : newPassword.hashCode());
		result = prime * result + ((oldPassword == null) ? 0 : oldPassword.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((submoduleCode == null) ? 0 : submoduleCode.hashCode());
		result = prime * result + ((submoduleName == null) ? 0 : submoduleName.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((userFirstName == null) ? 0 : userFirstName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userLastName == null) ? 0 : userLastName.hashCode());
		result = prime * result + ((userMiddleName == null) ? 0 : userMiddleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginForm other = (LoginForm) obj;
		if (confirmPassword == null) {
			if (other.confirmPassword != null)
				return false;
		} else if (!confirmPassword.equals(other.confirmPassword))
			return false;
		if (eMail == null) {
			if (other.eMail != null)
				return false;
		} else if (!eMail.equals(other.eMail))
			return false;
		if (informationDialog != other.informationDialog)
			return false;
		if (informationDialogHeader == null) {
			if (other.informationDialogHeader != null)
				return false;
		} else if (!informationDialogHeader.equals(other.informationDialogHeader))
			return false;
		if (informationDialogText == null) {
			if (other.informationDialogText != null)
				return false;
		} else if (!informationDialogText.equals(other.informationDialogText))
			return false;
		if (isValidUser == null) {
			if (other.isValidUser != null)
				return false;
		} else if (!isValidUser.equals(other.isValidUser))
			return false;
		if (loginId == null) {
			if (other.loginId != null)
				return false;
		} else if (!loginId.equals(other.loginId))
			return false;
		if (moduleCode == null) {
			if (other.moduleCode != null)
				return false;
		} else if (!moduleCode.equals(other.moduleCode))
			return false;
		if (moduleName == null) {
			if (other.moduleName != null)
				return false;
		} else if (!moduleName.equals(other.moduleName))
			return false;
		if (newPassword == null) {
			if (other.newPassword != null)
				return false;
		} else if (!newPassword.equals(other.newPassword))
			return false;
		if (oldPassword == null) {
			if (other.oldPassword != null)
				return false;
		} else if (!oldPassword.equals(other.oldPassword))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (submoduleCode == null) {
			if (other.submoduleCode != null)
				return false;
		} else if (!submoduleCode.equals(other.submoduleCode))
			return false;
		if (submoduleName == null) {
			if (other.submoduleName != null)
				return false;
		} else if (!submoduleName.equals(other.submoduleName))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (userFirstName == null) {
			if (other.userFirstName != null)
				return false;
		} else if (!userFirstName.equals(other.userFirstName))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userLastName == null) {
			if (other.userLastName != null)
				return false;
		} else if (!userLastName.equals(other.userLastName))
			return false;
		if (userMiddleName == null) {
			if (other.userMiddleName != null)
				return false;
		} else if (!userMiddleName.equals(other.userMiddleName))
			return false;
		return true;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	//Setter and Getter Method 
	//---------------------------------------------------------------------------------------------------------------------
	
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getSubmoduleName() {
		return submoduleName;
	}
	public void setSubmoduleName(String submoduleName) {
		this.submoduleName = submoduleName;
	}
	public String getSubmoduleCode() {
		return submoduleCode;
	}
	public void setSubmoduleCode(String submoduleCode) {
		this.submoduleCode = submoduleCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserMiddleName() {
		return userMiddleName;
	}
	public void setUserMiddleName(String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String mail) {
		eMail = mail;
	}
	public String getIsValidUser() {
		return isValidUser;
	}
	public void setIsValidUser(String isValidUser) {
		this.isValidUser = isValidUser;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}	
	public boolean isInformationDialog() {
		return informationDialog;
	}
	public void setInformationDialog(boolean informationDialog) {
		this.informationDialog = informationDialog;
	}
	public String getInformationDialogText() {
		return informationDialogText;
	}
	public void setInformationDialogText(String informationDialogText) {
		this.informationDialogText = informationDialogText;
	}
	public String getInformationDialogHeader() {
		return informationDialogHeader;
	}
	public void setInformationDialogHeader(String informationDialogHeader) {
		this.informationDialogHeader = informationDialogHeader;
	}
}
