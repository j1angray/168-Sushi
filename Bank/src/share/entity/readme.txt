package application.share.entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * Bank account entity
 * @author
 *
 */
public class BankAccount {
	private final SimpleStringProperty bankName;
	private final SimpleStringProperty bankAccount;
	private final SimpleStringProperty bankKey;
	private final SimpleStringProperty money;
	private final SimpleStringProperty state;
	private final SimpleStringProperty getKey;


	public BankAccount(String bankName, String bankAccount, String bankKey,String money,String state,String getKey) {
        this.bankName = new SimpleStringProperty(bankName);
        this.bankAccount = new SimpleStringProperty(bankAccount);
        this.bankKey = new SimpleStringProperty(bankKey);
        this.money = new SimpleStringProperty(money);
        this.state = new SimpleStringProperty(state);
        this.getKey = new SimpleStringProperty(getKey);

    }
	public String getBankName() {
		return bankName.get();
	}
	public void setBankName(String bankName) {
		this.bankName.set(bankName);
	}
	public String getBankAccount() {
		return bankAccount.get();
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount.set(bankAccount);
	}
	public String getBankKey() {
		return bankKey.get();
	}
	public void setBankKey(String bankKey) {
		this.bankKey.set(bankKey);
	}
	public String getMoney() {
		return money.get();
	}
	public void setMoney(String money) {
		this.money.set(money);
	}
	public String getState() {
		return state.get();
	}
	public void setState(String state) {
		this.state.set(state);
	}
	public String getGetKey() {
		return getKey.get();
	}
	public void setGetKey(String getKey) {
		this.getKey.set(getKey);
	}


}


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

package application.share.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserRequestTest {

    private UserRequest userRequestUnderTest;

    @Before
    public void setUp() {
        userRequestUnderTest = new UserRequest("userName", "bankKey", "date", "type", "id", "typevalue");
    }

    @Test
    public void testGetUserName() {
        // Setup

        // Run the test
        final String result = userRequestUnderTest.getUserName();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetBankKey() {
        // Setup

        // Run the test
        final String result = userRequestUnderTest.getBankKey();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetDate() {
        // Setup

        // Run the test
        final String result = userRequestUnderTest.getDate();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetType() {
        // Setup

        // Run the test
        final String result = userRequestUnderTest.getType();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetId() {
        // Setup

        // Run the test
        final String result = userRequestUnderTest.getId();

        // Verify the results
        assertEquals("result", result);
    }
}
