package com.sky.entity;

/**
 * @author 施 凯 沅
 * @version 0.0.1
 */
public class ChatContext {

    /**
     * data : {"keyWord":"1","reply":"MM才不+1呢，哼唧"}
     * isSuccess : true
     * code : 0
     * message :
     */

    private DataBean data;
    private boolean isSuccess;
    private String code;
    private String message;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * keyWord : 1
         * reply : MM才不+1呢，哼唧
         */

        private String keyWord;
        private String reply;

        public String getKeyWord() {
            return keyWord;
        }

        public void setKeyWord(String keyWord) {
            this.keyWord = keyWord;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }
    }
}
