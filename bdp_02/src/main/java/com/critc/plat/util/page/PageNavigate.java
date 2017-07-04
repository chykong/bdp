package com.critc.plat.util.page;

import com.critc.plat.util.global.GlobalConst;

/**
 * 类说明：页码显示效果类 。1：TextModel “第一页 上一页 下一页 最后一页”；2：NumModel “第一页 2 3 4 最后一页”；
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class PageNavigate {
    /**
     * 文本类型：形如：“第一页 上一页 下一页 最后一页”
     */
    public static final int TEXT_MODEL = 1;

    /**
     * 数字类型：形如：NumModel “第一页 2 3 4 最后一页”
     */
    public static final int NUM_MODEL = 2;
    /**
     * 页码显示模型
     */
    private int model = 2;
    /**
     * 页码连接URL，不需要添加页码参数
     */
    private String url;

    /**
     * 当前页
     */
    private int pageIndex;

    /**
     * 每页多少条记录
     */
    private int pageSize;//

    /**
     * 记录总数
     */
    private int recordCount;//

    /**
     * 模型类型的页码
     */
    private StringBuffer strHtml = null;

    /**
     * 数字类型的页码模型中间数字显示个数，例如：第一页 1 2 3 4 5 最后一页，numCount = 5; 默认显示 5个数字
     */
    private int numCount = 5;

    private int countPage;// 共有多少页

    /**
     * 页码的模式默认的文字类型的样式
     *
     * @param url 页面的url地址
     */
    public PageNavigate(String url, int pageIndex, int pageSize, int recordCount) {
        super();
        this.url = url;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.recordCount = recordCount;
        countPage = calPageCount(recordCount, pageSize);
        if (pageIndex > countPage && countPage > 0)
            this.pageIndex = countPage;
    }

    /**
     * 只传入url、当前页、总记录数
     *
     * @param url
     * @param pageIndex
     * @param recordCount
     */
    public PageNavigate(String url, int pageIndex, int recordCount) {
        super();
        this.url = url;
        this.pageIndex = pageIndex;
        this.pageSize = GlobalConst.PAGESIZE;
        this.recordCount = recordCount;
        countPage = calPageCount(recordCount, pageSize);
        if (pageIndex > countPage && countPage > 0)
            this.pageIndex = countPage;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    /**
     * 页码的模型
     * <p>
     * 页面信息
     *
     * @param url   页面的url地址
     * @param model 页码的显示样式
     */
    public PageNavigate(String url, int pageIndex, int pageSize, int recordCount, int model) {
        super();
        this.model = model;
        this.url = url;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.recordCount = recordCount;
    }

    /**
     * 页码的模型
     * <p>
     *
     * @param url      页面的url地址
     * @param model    页码的显示样式
     * @param numCount 数字类型的页码，共显示的个数
     */
    public PageNavigate(String url, int pageIndex, int pageSize, int recordCount, int model, int numCount) {
        super();
        this.model = model;
        this.url = url;
        this.numCount = numCount;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.recordCount = recordCount;
    }

    /**
     * 返回页面的模型
     *
     * @return
     */
    public String getPageModel() {
        // 组装页码模型
        createURL();
        return createModel();
    }

    /**
     * 构建URL
     */
    private void createURL() {
        url = url.contains("?") ? url + "&pageIndex=" : url + "?pageIndex=";
    }

    /**
     * 组装页码模型
     */
    private String createModel() {
        strHtml = new StringBuffer();
        switch (model) {
            case TEXT_MODEL:// 文本模型
                buildTextModel();
                break;
            case NUM_MODEL:// 数字模型
                buildNumModel();
                break;
            default:// 文本模型
                buildNumModel();
                break;
        }
        return strHtml.toString();
    }

    /**
     * 组件数字类型的页码模型
     */
    private void buildNumModel() {
        int currentPage = pageIndex > countPage ? 1 : pageIndex;
        if (recordCount != 0) {
            strHtml.append("<div class=\"col-xs-6 text-left\"><ul class=\"pagination\">");
            // 构造格式：第一页 1 2 3 4 5 最后一页
            PageIndex pageIndex = PageIndex.getPageIndex(numCount, currentPage, countPage);
            // 不是第一页时，显示首页
            if (currentPage > 1) {
                strHtml.append("<li><a href=\"").append(url).append(1).append("\"><i class=\"fa fa-angle-double-left\"></i></a></li>");
                // 显示上一页
                strHtml.append("<li><a href=\"").append(url).append(currentPage - 1).append("\"><i class=\"fa fa-angle-left\"></i></a></li>");
            } else {
                strHtml.append("<li class=\"disabled\"><a href=\"javascript:;").append("\"><i class=\"fa fa-angle-double-left\"></i></a></li>");
                // 显示上一页
                strHtml.append("<li class=\"disabled\"><a href=\"javascript:;").append("\"><i class=\"fa fa-angle-left\"></i></a></li>");
            }

            if (currentPage <= countPage) {
                for (int i = pageIndex.getStartIndex(); i <= pageIndex.getEndIndex(); i++) {
                    if (currentPage == i) {
                        strHtml.append("<li class=\"active\"><a href=\"javascript:;").append("\">").append(i).append(" </a></li>");
                    } else {
                        strHtml.append("<li><a href=\"").append(url).append(i).append("\">").append(i).append(" </a></li>");
                    }
                }

            }
            // 显示下一页
            if (currentPage < countPage) {
                strHtml.append("<li><a href=\"").append(url).append(currentPage + 1).append("\"><i class=\"fa fa-angle-right\"></i></a></li>");
                // 显示最后一页
                strHtml.append("<li><a href=\"").append(url).append(countPage).append("\"> <i class=\"fa fa-angle-double-right\"></i></a></li>");
            } else {
                strHtml.append("<li class=\"disabled\"><a href=\"javascript:;").append("\"><i class=\"fa fa-angle-right\"></i></a></li>");
                // 显示最后一页
                strHtml.append("<li class=\"disabled\"><a href=\"javascript:;").append("\"><i class=\"fa fa-angle-double-right\"></i></a></li>");
            }
            strHtml.append("</ul></div>");
        } else {
            strHtml.append("<div class=\"col-xs-6 maringTop10 text-right\"></div>");
        }
        if (recordCount == 0)
            strHtml.append("<div class=\"col-xs-6 maringTop10 text-right\">没有数据</div>");
        else
            strHtml.append("<div class=\"col-xs-6 maringTop10 text-right\">当前显示").append(calCurShow(currentPage, pageSize, recordCount, countPage)).append("条,共").append(recordCount)
                    .append("条记录</div>");
    }

    /**
     * 组件文本类型的页码
     */
    private void buildTextModel() {
        int currentPage = pageIndex > countPage ? 1 : pageIndex;
        strHtml.append("<table width='100%'  border='0' cellspacing='0' cellpadding='0'>").append("<tr> <td height='24' align='center'>当前页数：[").append(currentPage).append("/").append(countPage)
                .append("]&nbsp;&nbsp;");
        if (currentPage > 1) {
            strHtml.append("<a href='").append(url).append("1'>首页</a>").append("&nbsp;&nbsp;<a href='").append(url).append(currentPage - 1).append("'>上一页</a>");
        }
        if (currentPage < countPage) {
            strHtml.append("&nbsp;&nbsp;<a href='").append(url).append(currentPage + 1).append("'>下一页</a>&nbsp;&nbsp;<a href='").append(url).append(countPage).append("'>末页</a>");
        }
        strHtml.append("</td></tr></table>");
    }

    /**
     * 计算总共有多少页
     *
     * @return
     */
    private int calPageCount(int recordCount, int pageSize) {
        return (recordCount % pageSize == 0) ? (recordCount / pageSize) : (recordCount / pageSize + 1);
    }

    // 计算当前显示的记录数
    private String calCurShow(int pageIndex, int pageSize, int recordCount, int pageCount) {
        String str = "";
        if (pageIndex < pageCount)
            str = (pageIndex - 1) * pageSize + 1 + "-" + ((pageIndex - 1) * pageSize + pageSize);
        else {
            str = (pageIndex - 1) * pageSize + 1 + "-" + (recordCount);
        }
        return str;
    }
}
