package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Table to maintain images of user.
 */
@Entity
@Table(name = "USER_IMAGES")
public class UserImages extends AuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = -1L;

    /** Primary key for event status. */
    private Long id;

    /** User image is associated with. */
    private User user;

    /** Image url. */
    private String imageUrl;

    /** Image height. */
    private Integer imageHeight;

    /** Image width. */
    private Integer imageWidth;

    /** Image comment. */
    private String imageComment;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user.
     * 
     * @return the userId
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    /**
     * Sets the user.
     * 
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the image url.
     * 
     * @return the imageUrl
     */
    @Column(name = "IMAGE_URL")
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image url.
     * 
     * @param imageUrl the imageUrl to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the image height.
     * 
     * @return the imageHeight
     */
    @Column(name = "IMAGE_HEIGHT")
    public Integer getImageHeight() {
        return imageHeight;
    }

    /**
     * Sets the image height.
     * 
     * @param imageHeight the imageHeight to set
     */
    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * Gets the image width.
     * 
     * @return the imageWidth
     */
    @Column(name = "IMAGE_WIDTH")
    public Integer getImageWidth() {
        return imageWidth;
    }

    /**
     * Sets the image width.
     * 
     * @param imageWidth the imageWidth to set
     */
    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * Gets the image comment.
     * 
     * @return the imageComment
     */
    @Column(name = "IMAGE_COMMENT")
    public String getImageComment() {
        return imageComment;
    }

    /**
     * Sets the image comment.
     * 
     * @param imageComment the imageComment to set
     */
    public void setImageComment(String imageComment) {
        this.imageComment = imageComment;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    @Override
    public String toString() {

        final String tab = "    ";
        return new StringBuilder("UserImages ( ").append("image-url = ")
                .append(imageUrl).append(tab).append("imageHeight= ").append(
                        imageHeight).append(tab).append("imageWidth= ").append(
                        imageWidth).append(tab).append("imageComment= ")
                .append(imageComment).append(tab).append(super.toString())
                .append(" )").toString();
    }
}
