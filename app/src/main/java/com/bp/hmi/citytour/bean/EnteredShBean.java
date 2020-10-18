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
     * result : {"pageSize":10,"currentPage":0,"items":[{"id":2,"name":"中共一大会址纪念馆","recommendPoint":1,"spendTime":2,"cover":"uploadCityWalk/zouJinSH/1b197b3e7aea45a98a5ccd5ca9855445.png","summary":"景点地址：上海市黄浦区黄陂南路374号\r\n开放时间：09:00-17:00开放（16:00停止入园）\r\n官方电话：021-53832171","content":null,"createTime":"2020-09-13 18:58:18"},{"id":3,"name":"孙中山故居纪念馆","recommendPoint":3,"spendTime":4,"cover":"uploadCityWalk/zouJinSH/3ed19878ca1243828dc8266389cf6baa.png","summary":"景点地址：上海市黄浦区香山路7号\r\n开放时间：周二-周日9:00-17:00，16:30停止售票；周一闭馆（除国家法定    节假日外）。\r\n官方电话：021-53063361,021-63858283,021-63858251,021-64372954","content":null,"createTime":"2020-09-13 19:06:33"},{"id":4,"name":"上海市龙华烈士陵园","recommendPoint":5,"spendTime":7,"cover":"uploadCityWalk/zouJinSH/1ddb256397a04f80824e69428abc77b7.png","summary":"景点地址：上海市徐汇区龙华西路180号\r\n开放时间：6:00-17:30（17:00停止入园，17:30清场。）纪念馆：9:00-16:30  (16:00停止进馆，周一闭馆)\r\n官方电话：021-64685995,021-64683059","content":null,"createTime":"2020-09-13 19:08:25"},{"id":5,"name":"毛泽东旧居","recommendPoint":2,"spendTime":3,"cover":"uploadCityWalk/zouJinSH/6bca9f97a8df4a07b39a0f0b214d54e1.png","summary":"景点地址：上海市静安区茂名北路120弄\r\n开放时间：周二-周日9:00-14:30，周一闭馆。\r\n官方电话：021-62723656","content":null,"createTime":"2020-09-13 19:09:32"},{"id":6,"name":"宋庆龄陵园","recommendPoint":3,"spendTime":5,"cover":"uploadCityWalk/zouJinSH/7f2e85b7fa17466a9297f83990c7dcd3.png","summary":"景点地址：上海市长宁区宋园路21号\r\n开放时间：8:30-17:00，16:30起停止入园，17:00闭园清场；室内场馆另见场馆开放办法。\r\n官方电话：021-62754034","content":null,"createTime":"2020-09-13 19:11:16"}],"totalPage":1,"totalCount":5}
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

    public static class ResultBean implements Serializable {
        /**
         * pageSize : 10
         * currentPage : 0
         * items : [{"id":2,"name":"中共一大会址纪念馆","recommendPoint":1,"spendTime":2,"cover":"uploadCityWalk/zouJinSH/1b197b3e7aea45a98a5ccd5ca9855445.png","summary":"景点地址：上海市黄浦区黄陂南路374号\r\n开放时间：09:00-17:00开放（16:00停止入园）\r\n官方电话：021-53832171","content":null,"createTime":"2020-09-13 18:58:18"},{"id":3,"name":"孙中山故居纪念馆","recommendPoint":3,"spendTime":4,"cover":"uploadCityWalk/zouJinSH/3ed19878ca1243828dc8266389cf6baa.png","summary":"景点地址：上海市黄浦区香山路7号\r\n开放时间：周二-周日9:00-17:00，16:30停止售票；周一闭馆（除国家法定    节假日外）。\r\n官方电话：021-53063361,021-63858283,021-63858251,021-64372954","content":null,"createTime":"2020-09-13 19:06:33"},{"id":4,"name":"上海市龙华烈士陵园","recommendPoint":5,"spendTime":7,"cover":"uploadCityWalk/zouJinSH/1ddb256397a04f80824e69428abc77b7.png","summary":"景点地址：上海市徐汇区龙华西路180号\r\n开放时间：6:00-17:30（17:00停止入园，17:30清场。）纪念馆：9:00-16:30  (16:00停止进馆，周一闭馆)\r\n官方电话：021-64685995,021-64683059","content":null,"createTime":"2020-09-13 19:08:25"},{"id":5,"name":"毛泽东旧居","recommendPoint":2,"spendTime":3,"cover":"uploadCityWalk/zouJinSH/6bca9f97a8df4a07b39a0f0b214d54e1.png","summary":"景点地址：上海市静安区茂名北路120弄\r\n开放时间：周二-周日9:00-14:30，周一闭馆。\r\n官方电话：021-62723656","content":null,"createTime":"2020-09-13 19:09:32"},{"id":6,"name":"宋庆龄陵园","recommendPoint":3,"spendTime":5,"cover":"uploadCityWalk/zouJinSH/7f2e85b7fa17466a9297f83990c7dcd3.png","summary":"景点地址：上海市长宁区宋园路21号\r\n开放时间：8:30-17:00，16:30起停止入园，17:00闭园清场；室内场馆另见场馆开放办法。\r\n官方电话：021-62754034","content":null,"createTime":"2020-09-13 19:11:16"}]
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

        public static class ItemsBean implements Serializable {
            /**
             * id : 2
             * name : 中共一大会址纪念馆
             * recommendPoint : 1
             * spendTime : 2
             * cover : uploadCityWalk/zouJinSH/1b197b3e7aea45a98a5ccd5ca9855445.png
             * summary : 景点地址：上海市黄浦区黄陂南路374号
             * 开放时间：09:00-17:00开放（16:00停止入园）
             * 官方电话：021-53832171
             * content : null
             * createTime : 2020-09-13 18:58:18
             */

            private int id;
            private String name;
            private int recommendPoint;
            private int spendTime;
            private String cover;
            private String summary;
            private Object content;
            private String createTime;

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

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
