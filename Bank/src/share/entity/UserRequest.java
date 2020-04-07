
package application.share.entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * User request record
 * @author
 *
 */
public class UserRequest {
		private  SimpleStringProperty userName;
		private  SimpleStringProperty bankKey;
		private  SimpleStringProperty date;
		private  SimpleStringProperty type;
		private String typevalue;
		private SimpleStringProperty id;
		public UserRequest(String userName, String bankKey, String date,String type,String id,String typevalue) {
			this.userName = new SimpleStringProperty(userName);
			this.bankKey = new SimpleStringProperty(bankKey);
			this.date = new SimpleStringProperty(date);
			this.type = new SimpleStringProperty(type);
			this.id = new SimpleStringProperty(id);
			this.typevalue=typevalue;
		}


		public String getTypevalue() {
			return typevalue;
		}


		public void setTypevalue(String typevalue) {
			this.typevalue = typevalue;
		}


		public String getUserName() {
			return userName.get();
		}

		public void setUserName(String userName) {
			this.userName.set(userName);
		}

		public String getBankKey() {
			return bankKey.get();
		}

		public void setBankKey(String bankKey) {
			this.bankKey.set(bankKey);
		}

		public String getDate() {
			return date.get();
		}

		public void setDate(String date) {
			this.date.set(date);
		}

		public String getType() {
			return type.get();
		}

		public void setType(String type) {
			this.type.set(type);
		}

		public String getId() {
			return id.get();
		}

		public void setId(String id) {
			this.id.set(id);
		}



}
