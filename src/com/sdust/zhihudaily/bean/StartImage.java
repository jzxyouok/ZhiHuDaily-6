package com.sdust.zhihudaily.bean;

/**
 * ������StartImage
 * ˵������ʼ�����bean��
 */
public class StartImage {
	
	private String text;
	private String img;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public boolean isValueEquals(StartImage startImage) {
        return this.text.equals(startImage.getText()) && this.img.equals(startImage.getImg());
    }
	

}
