package com.monash.flickr.model;
import java.util.ArrayList;

public class Message {

    private ArrayList<String> images;
    private String message;
    
    public Message() {
    	this.images = new ArrayList<String>();
    }
 
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
    
    public void addImage(String image) {
        this.images.add(image);
    }

}
