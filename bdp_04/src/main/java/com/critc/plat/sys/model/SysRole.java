package com.critc.plat.sys.model;

import java.util.Date;

/**
 * 系统角色
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
public class SysRole {
    private int id;// 角色id
    private String name;// 角色名称
    private String description;//描述
    private int displayOrder;//排序

    private String createdBy;//创建人
    private Date createdAt;//创建时间
    private String lastModifiedBy;//最后修改人
    private Date lastModifiedAt;//最后修改时间

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", displayOrder=" + displayOrder +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedAt=" + lastModifiedAt +
                '}';
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Date lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
