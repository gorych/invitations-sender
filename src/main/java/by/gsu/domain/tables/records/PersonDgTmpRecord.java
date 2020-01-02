/*
 * This file is generated by jOOQ.
 */
package by.gsu.domain.tables.records;


import by.gsu.domain.tables.PersonDgTmp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PersonDgTmpRecord extends UpdatableRecordImpl<PersonDgTmpRecord> implements Record5<Integer, String, String, String, String> {

    private static final long serialVersionUID = -698364679;

    /**
     * Setter for <code>person_dg_tmp.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>person_dg_tmp.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>person_dg_tmp.firstName</code>.
     */
    public void setFirstname(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>person_dg_tmp.firstName</code>.
     */
    public String getFirstname() {
        return (String) get(1);
    }

    /**
     * Setter for <code>person_dg_tmp.lastName</code>.
     */
    public void setLastname(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>person_dg_tmp.lastName</code>.
     */
    public String getLastname() {
        return (String) get(2);
    }

    /**
     * Setter for <code>person_dg_tmp.email</code>.
     */
    public void setEmail(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>person_dg_tmp.email</code>.
     */
    public String getEmail() {
        return (String) get(3);
    }

    /**
     * Setter for <code>person_dg_tmp.middleName</code>.
     */
    public void setMiddlename(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>person_dg_tmp.middleName</code>.
     */
    public String getMiddlename() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, String, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, String, String, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return PersonDgTmp.PERSON_DG_TMP.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return PersonDgTmp.PERSON_DG_TMP.FIRSTNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return PersonDgTmp.PERSON_DG_TMP.LASTNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return PersonDgTmp.PERSON_DG_TMP.EMAIL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return PersonDgTmp.PERSON_DG_TMP.MIDDLENAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getFirstname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getLastname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getMiddlename();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getFirstname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getLastname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getMiddlename();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonDgTmpRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonDgTmpRecord value2(String value) {
        setFirstname(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonDgTmpRecord value3(String value) {
        setLastname(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonDgTmpRecord value4(String value) {
        setEmail(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonDgTmpRecord value5(String value) {
        setMiddlename(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonDgTmpRecord values(Integer value1, String value2, String value3, String value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PersonDgTmpRecord
     */
    public PersonDgTmpRecord() {
        super(PersonDgTmp.PERSON_DG_TMP);
    }

    /**
     * Create a detached, initialised PersonDgTmpRecord
     */
    public PersonDgTmpRecord(Integer id, String firstname, String lastname, String email, String middlename) {
        super(PersonDgTmp.PERSON_DG_TMP);

        set(0, id);
        set(1, firstname);
        set(2, lastname);
        set(3, email);
        set(4, middlename);
    }
}
