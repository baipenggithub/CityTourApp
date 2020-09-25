package com.bp.hmi.citytour.bean;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *     author : BaiPengMac
 *     e-mail : xxx@xx
 *     time   : 2020/09/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class EnteredShBean implements Serializable {

    /**
     * code : 1
     * msg : 操作成功
     * result : {"pageSize":10,"currentPage":0,"items":[{"id":2,"name":"中共一大会址纪念馆","recommendPoint":1,"spendTime":2,"cover":"uploadCityWalk/zouJinSH/1b197b3e7aea45a98a5ccd5ca9855445.png","summary":"景点地址：上海市黄浦区黄陂南路374号 开放时间：09:00-17:00开放（16:00停止入园） 官方电话：021-53832171","content":"","createTime":"2020-09-13 18:58:18","issuance":3,"issuanceStr":"已发布","seq":null},{"id":3,"name":"孙中山故居纪念馆","recommendPoint":3,"spendTime":4,"cover":"uploadCityWalk/zouJinSH/3ed19878ca1243828dc8266389cf6baa.png","summary":"景点地址：上海市黄浦区香山路7号 开放时间：周二-周日9:00-17:00，16:30停止售票；周一闭馆（除国家法定 节假日外）。 官方电话：021-53063361,021-63858283,021-63858251,021-64372954","content":"","createTime":"2020-09-13 19:06:33","issuance":3,"issuanceStr":"已发布","seq":null},{"id":6,"name":"宋庆龄陵园","recommendPoint":3,"spendTime":5,"cover":"uploadCityWalk/zouJinSH/7f2e85b7fa17466a9297f83990c7dcd3.png","summary":"景点地址：上海市长宁区宋园路21号 开放时间：8:30-17:00，16:30起停止入园，17:00闭园清场；室内场馆另见场馆开放办法。 官方电话：021-62754034","content":"","createTime":"2020-09-13 19:11:16","issuance":3,"issuanceStr":"已发布","seq":null}],"totalPage":1,"totalCount":5}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * pageSize : 10
         * currentPage : 0
         * items : [{"id":2,"name":"中共一大会址纪念馆","recommendPoint":1,"spendTime":2,"cover":"uploadCityWalk/zouJinSH/1b197b3e7aea45a98a5ccd5ca9855445.png","summary":"景点地址：上海市黄浦区黄陂南路374号 开放时间：09:00-17:00开放（16:00停止入园） 官方电话：021-53832171","content":"","createTime":"2020-09-13 18:58:18","issuance":3,"issuanceStr":"已发布","seq":null},{"id":3,"name":"孙中山故居纪念馆","recommendPoint":3,"spendTime":4,"cover":"uploadCityWalk/zouJinSH/3ed19878ca1243828dc8266389cf6baa.png","summary":"景点地址：上海市黄浦区香山路7号 开放时间：周二-周日9:00-17:00，16:30停止售票；周一闭馆（除国家法定 节假日外）。 官方电话：021-53063361,021-63858283,021-63858251,021-64372954","content":"","createTime":"2020-09-13 19:06:33","issuance":3,"issuanceStr":"已发布","seq":null},{"id":6,"name":"宋庆龄陵园","recommendPoint":3,"spendTime":5,"cover":"uploadCityWalk/zouJinSH/7f2e85b7fa17466a9297f83990c7dcd3.png","summary":"景点地址：上海市长宁区宋园路21号 开放时间：8:30-17:00，16:30起停止入园，17:00闭园清场；室内场馆另见场馆开放办法。 官方电话：021-62754034","content":"","createTime":"2020-09-13 19:11:16","issuance":3,"issuanceStr":"已发布","seq":null}]
         * totalPage : 1
         * totalCount : 5
         */

        private int pageSize;
        private int currentPage;
        private int totalPage;
        private int totalCount;
        private List<ItemsBean> items;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * id : 2
             * name : 中共一大会址纪念馆
             * recommendPoint : 1
             * spendTime : 2
             * cover : uploadCityWalk/zouJinSH/1b197b3e7aea45a98a5ccd5ca9855445.png
             * summary : 景点地址：上海市黄浦区黄陂南路374号 开放时间：09:00-17:00开放（16:00停止入园） 官方电话：021-53832171
             * content :
             * createTime : 2020-09-13 18:58:18
             * issuance : 3
             * issuanceStr : 已发布
             * seq : null
             */

            private int id;
            private String name;
            private int recommendPoint;
            private int spendTime;
            private String cover;
            private String summary;
            private String content;
            private String createTime;
            private int issuance;
            private String issuanceStr;
            private Object seq;

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

            public int getRecommendPoint() {
                return recommendPoint;
            }

            public void setRecommendPoint(int recommendPoint) {
                this.recommendPoint = recommendPoint;
            }

            public int getSpendTime() {
                return spendTime;
            }

            public void setSpendTime(int spendTime) {
                this.spendTime = spendTime;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getIssuance() {
                return issuance;
            }

            public void setIssuance(int issuance) {
                this.issuance = issuance;
            }

            public String getIssuanceStr() {
                return issuanceStr;
            }

            public void setIssuanceStr(String issuanceStr) {
                this.issuanceStr = issuanceStr;
            }

            public Object getSeq() {
                return seq;
            }

            public void setSeq(Object seq) {
                this.seq = seq;
            }
        }
    }
}
