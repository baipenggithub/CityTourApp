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
public class ActivityTabBean implements Serializable {


    /**
     * code : 1
     * msg : 操作成功
     * result : {"pageSize":10,"currentPage":0,"items":[{"id":21,"name":"上海国金中心商场","cover":"uploadCityWalk/activity/a3c0905693024d8aa137b642941cf3ae.png","summary":"地        址：上海市浦东新区世纪大道8号\r\n电        话：021-20207070\r\n官方网站：http://www.shanghaiifcmall.com.cn/cn/index.html\r\n营业时间：10:00-22:00","content":null,"pingfen":"","time":"","address":"上海市浦东新区世纪大道8号","type":3,"isNew":0,"createTime":"2020-09-13 21:51:42"},{"id":22,"name":"新世界城","cover":"uploadCityWalk/activity/534d405d6faf4a6f80190b54a1abd2d7.png","summary":"地        址：上海市黄浦区南京西路2-68号\r\n电        话：021-63588888\r\n营业时间：10:00-22:00","content":null,"pingfen":"","time":"","address":"上海市黄浦区南京西路2-68号","type":3,"isNew":0,"createTime":"2020-09-13 21:54:00"}],"totalPage":1,"totalCount":2}
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
         * items : [{"id":21,"name":"上海国金中心商场","cover":"uploadCityWalk/activity/a3c0905693024d8aa137b642941cf3ae.png","summary":"地        址：上海市浦东新区世纪大道8号\r\n电        话：021-20207070\r\n官方网站：http://www.shanghaiifcmall.com.cn/cn/index.html\r\n营业时间：10:00-22:00","content":null,"pingfen":"","time":"","address":"上海市浦东新区世纪大道8号","type":3,"isNew":0,"createTime":"2020-09-13 21:51:42"},{"id":22,"name":"新世界城","cover":"uploadCityWalk/activity/534d405d6faf4a6f80190b54a1abd2d7.png","summary":"地        址：上海市黄浦区南京西路2-68号\r\n电        话：021-63588888\r\n营业时间：10:00-22:00","content":null,"pingfen":"","time":"","address":"上海市黄浦区南京西路2-68号","type":3,"isNew":0,"createTime":"2020-09-13 21:54:00"}]
         * totalPage : 1
         * totalCount : 2
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
             * id : 21
             * name : 上海国金中心商场
             * cover : uploadCityWalk/activity/a3c0905693024d8aa137b642941cf3ae.png
             * summary : 地        址：上海市浦东新区世纪大道8号
             * 电        话：021-20207070
             * 官方网站：http://www.shanghaiifcmall.com.cn/cn/index.html
             * 营业时间：10:00-22:00
             * content : null
             * pingfen :
             * time :
             * address : 上海市浦东新区世纪大道8号
             * type : 3
             * isNew : 0
             * createTime : 2020-09-13 21:51:42
             */

            private int id;
            private String name;
            private String cover;
            private String summary;
            private Object content;
            private String pingfen;
            private String time;
            private String address;
            private int type;
            private int isNew;
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

            public String getPingfen() {
                return pingfen;
            }

            public void setPingfen(String pingfen) {
                this.pingfen = pingfen;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getIsNew() {
                return isNew;
            }

            public void setIsNew(int isNew) {
                this.isNew = isNew;
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
