package com.deploymentplan.client.user;

import com.deploymentplan.domain.user.User;
import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class LoginWindow extends Window {
	private LoginWindowInterface mOBLoginWindowInterface = null;
	private VLayout mOBCanvasUsernamePassword = null;

	public LoginWindow(LoginWindowInterface pOBLoginWindowInterface) {
		mOBLoginWindowInterface = pOBLoginWindowInterface;
		setAutoSize(true);
		setTitle("Login Information");
		setCanDragReposition(true);
		setCanDragResize(true);
		setLeft(130);
		setTop(15);
		setShowCloseButton(false);
		setShowMinimizeButton(false);
		addItem(getCanvasUsernamePassword());
	}

	private VLayout getCanvasUsernamePassword() {
		if (mOBCanvasUsernamePassword == null) {
			mOBCanvasUsernamePassword = new VLayout();

			final DynamicForm form = new DynamicForm();
			form.setWidth(250);

			final TextItem usernameItem = new TextItem();
			usernameItem.setTitle("Username");
			usernameItem.setRequired(true);
			usernameItem.setDefaultValue("");

			final PasswordItem passwordItem = new PasswordItem();
			passwordItem.setTitle("Password");
			passwordItem.setRequired(true);
			
			passwordItem.addKeyPressHandler(new com.smartgwt.client.widgets.form.fields.events.KeyPressHandler() {
				
				@Override
				public void onKeyPress(com.smartgwt.client.widgets.form.fields.events.KeyPressEvent event) {
					if(event.getKeyName().equals(KeyNames.ENTER)) {
						LoginWindowServiceAsync lOBServiceAsync = GWT.create(LoginWindowService.class);
						User lOBUser = new User();
						lOBUser.setName(usernameItem.getDisplayValue());
						lOBUser.setPassword(passwordItem.getDisplayValue());
						lOBServiceAsync.login(lOBUser, mOBLoginWindowInterface.generateCallbackLogin(LoginWindow.this));						
					}
				}
			});
			
			IButton submit = new IButton();
			submit.setTitle("Submit");
			submit.setLayoutAlign(Alignment.RIGHT);
			submit.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					LoginWindowServiceAsync lOBServiceAsync = GWT.create(LoginWindowService.class);
					User lOBUser = new User();
					lOBUser.setName(usernameItem.getDisplayValue());
					lOBUser.setPassword(passwordItem.getDisplayValue());
					lOBServiceAsync.login(lOBUser, mOBLoginWindowInterface.generateCallbackLogin(LoginWindow.this));
				}
			});

			form.setFields(new FormItem[] { usernameItem, passwordItem });

			mOBCanvasUsernamePassword.addMember(form);
			mOBCanvasUsernamePassword.addMember(submit);

		}
		return mOBCanvasUsernamePassword;
	}

}
