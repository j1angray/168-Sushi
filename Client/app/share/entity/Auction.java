/***************************************************
 * Group member: Zihao Liu, Sun Su
 * The class of auction
 **************************************************/
package application.share.entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * Auction house entity
 * @author
 *
 */
public  class Auction {
	private  final SimpleStringProperty publicId;
    private final SimpleStringProperty name;
    private final SimpleStringProperty key;

    public Auction(String publicId, String name, String key) {
        this.publicId = new SimpleStringProperty(publicId);
        this.name = new SimpleStringProperty(name);
        this.key = new SimpleStringProperty(key);
    }

	public String getPublicId() {
		return publicId.get();
	}

	public void setPublicId(String publicId) {
		this.publicId .set(publicId);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getKey() {
		return key.get();
	}

	public void setKey(String key) {
		this.key.set(key);
	}




}
