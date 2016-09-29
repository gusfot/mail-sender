package com.wadiz.client.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientRequestExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    public ClientRequestExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andRequestSeqIsNull() {
            addCriterion("request_seq is null");
            return (Criteria) this;
        }

        public Criteria andRequestSeqIsNotNull() {
            addCriterion("request_seq is not null");
            return (Criteria) this;
        }

        public Criteria andRequestSeqEqualTo(Integer value) {
            addCriterion("request_seq =", value, "requestSeq");
            return (Criteria) this;
        }

        public Criteria andRequestSeqNotEqualTo(Integer value) {
            addCriterion("request_seq <>", value, "requestSeq");
            return (Criteria) this;
        }

        public Criteria andRequestSeqGreaterThan(Integer value) {
            addCriterion("request_seq >", value, "requestSeq");
            return (Criteria) this;
        }

        public Criteria andRequestSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("request_seq >=", value, "requestSeq");
            return (Criteria) this;
        }

        public Criteria andRequestSeqLessThan(Integer value) {
            addCriterion("request_seq <", value, "requestSeq");
            return (Criteria) this;
        }

        public Criteria andRequestSeqLessThanOrEqualTo(Integer value) {
            addCriterion("request_seq <=", value, "requestSeq");
            return (Criteria) this;
        }

        public Criteria andRequestSeqIn(List<Integer> values) {
            addCriterion("request_seq in", values, "requestSeq");
            return (Criteria) this;
        }

        public Criteria andRequestSeqNotIn(List<Integer> values) {
            addCriterion("request_seq not in", values, "requestSeq");
            return (Criteria) this;
        }

        public Criteria andRequestSeqBetween(Integer value1, Integer value2) {
            addCriterion("request_seq between", value1, value2, "requestSeq");
            return (Criteria) this;
        }

        public Criteria andRequestSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("request_seq not between", value1, value2, "requestSeq");
            return (Criteria) this;
        }

        public Criteria andRequestUuidIsNull() {
            addCriterion("request_uuid is null");
            return (Criteria) this;
        }

        public Criteria andRequestUuidIsNotNull() {
            addCriterion("request_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andRequestUuidEqualTo(String value) {
            addCriterion("request_uuid =", value, "requestUuid");
            return (Criteria) this;
        }

        public Criteria andRequestUuidNotEqualTo(String value) {
            addCriterion("request_uuid <>", value, "requestUuid");
            return (Criteria) this;
        }

        public Criteria andRequestUuidGreaterThan(String value) {
            addCriterion("request_uuid >", value, "requestUuid");
            return (Criteria) this;
        }

        public Criteria andRequestUuidGreaterThanOrEqualTo(String value) {
            addCriterion("request_uuid >=", value, "requestUuid");
            return (Criteria) this;
        }

        public Criteria andRequestUuidLessThan(String value) {
            addCriterion("request_uuid <", value, "requestUuid");
            return (Criteria) this;
        }

        public Criteria andRequestUuidLessThanOrEqualTo(String value) {
            addCriterion("request_uuid <=", value, "requestUuid");
            return (Criteria) this;
        }

        public Criteria andRequestUuidLike(String value) {
            addCriterion("request_uuid like", value, "requestUuid");
            return (Criteria) this;
        }

        public Criteria andRequestUuidNotLike(String value) {
            addCriterion("request_uuid not like", value, "requestUuid");
            return (Criteria) this;
        }

        public Criteria andRequestUuidIn(List<String> values) {
            addCriterion("request_uuid in", values, "requestUuid");
            return (Criteria) this;
        }

        public Criteria andRequestUuidNotIn(List<String> values) {
            addCriterion("request_uuid not in", values, "requestUuid");
            return (Criteria) this;
        }

        public Criteria andRequestUuidBetween(String value1, String value2) {
            addCriterion("request_uuid between", value1, value2, "requestUuid");
            return (Criteria) this;
        }

        public Criteria andRequestUuidNotBetween(String value1, String value2) {
            addCriterion("request_uuid not between", value1, value2, "requestUuid");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andRegTimeIsNull() {
            addCriterion("reg_time is null");
            return (Criteria) this;
        }

        public Criteria andRegTimeIsNotNull() {
            addCriterion("reg_time is not null");
            return (Criteria) this;
        }

        public Criteria andRegTimeEqualTo(Date value) {
            addCriterion("reg_time =", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeNotEqualTo(Date value) {
            addCriterion("reg_time <>", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeGreaterThan(Date value) {
            addCriterion("reg_time >", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reg_time >=", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeLessThan(Date value) {
            addCriterion("reg_time <", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeLessThanOrEqualTo(Date value) {
            addCriterion("reg_time <=", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeIn(List<Date> values) {
            addCriterion("reg_time in", values, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeNotIn(List<Date> values) {
            addCriterion("reg_time not in", values, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeBetween(Date value1, Date value2) {
            addCriterion("reg_time between", value1, value2, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeNotBetween(Date value1, Date value2) {
            addCriterion("reg_time not between", value1, value2, "regTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table request
     *
     * @mbggenerated do_not_delete_during_merge Mon Dec 21 19:30:25 KST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table request
     *
     * @mbggenerated Mon Dec 21 19:30:25 KST 2015
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}