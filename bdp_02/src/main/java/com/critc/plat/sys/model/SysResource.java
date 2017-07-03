package com.critc.plat.sys.model;


/**
 * 系统资源
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
public class SysResource {
    private int id;//编号
    private String name;//资源名称
    private String code;//资源代码
    private Integer parentId;//上级节点id
    private String parentName;//上级节点名称
    private String url;//链接
    private String iconImg;//图标
    private String target;//链接目标
    private int type;//资源类型1模块，2操作
    private String description;//描述
    private int displayOrder;//排序
    private int cnt;//子节点数量

    @Override
    public String toString() {
        return "SysResource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", parentId=" + parentId +
                ", parentName='" + parentName + '\'' +
                ", url='" + url + '\'' +
                ", iconImg='" + iconImg + '\'' +
                ", target='" + target + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", displayOrder=" + displayOrder +
                ", cnt=" + cnt +
                '}';
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
