package com.example.mywiki.mapper;

import com.example.mywiki.domain.Ebook;

public interface EbookMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ebook
     *
     * @mbg.generated Fri Jun 04 20:44:22 CST 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ebook
     *
     * @mbg.generated Fri Jun 04 20:44:22 CST 2021
     */
    int insert(Ebook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ebook
     *
     * @mbg.generated Fri Jun 04 20:44:22 CST 2021
     */
    int insertSelective(Ebook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ebook
     *
     * @mbg.generated Fri Jun 04 20:44:22 CST 2021
     */
    Ebook selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ebook
     *
     * @mbg.generated Fri Jun 04 20:44:22 CST 2021
     */
    int updateByPrimaryKeySelective(Ebook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ebook
     *
     * @mbg.generated Fri Jun 04 20:44:22 CST 2021
     */
    int updateByPrimaryKey(Ebook record);
}