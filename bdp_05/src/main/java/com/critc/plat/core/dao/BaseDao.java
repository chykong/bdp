package com.critc.plat.core.dao;

import com.critc.plat.util.model.ComboboxVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基础DAO，用于封装dao操作
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Component
public class BaseDao<T, S> {
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 新增
     *
     * @param sql
     * @param t
     * @return
     */
    protected int insert(String sql, T t) {
        return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(t));
    }

    /**
     * 新增并返回主键
     *
     * @param sql
     * @param t
     * @param pkField 主键字段
     * @return
     */
    protected int insertForId(String sql, T t, String pkField) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rc = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(t), keyHolder, new String[]{pkField});
        if (rc > 0) {
            return keyHolder.getKey().intValue();
        } else {
            return 0;
        }
    }

    /**
     * 修改，参数为model类
     *
     * @param sql
     * @param t
     * @return
     */
    protected int update(String sql, T t) {
        return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(t));
    }

    /**
     * 按照参数修改
     *
     * @param sql
     * @param objects
     * @return
     */
    protected int update(String sql, Object... objects) {
        return jdbcTemplate.update(sql, objects);
    }

    /**
     * 删除
     *
     * @param sql
     * @param objects
     * @return
     */
    protected int delete(String sql, Object... objects) {
        return jdbcTemplate.update(sql, objects);
    }

    /**
     * 根据参数获取model
     *
     * @param sql
     * @param objects
     * @return
     */
    protected T get(String sql, Object... objects) {
        List<T> list = jdbcTemplate.query(sql, objects, BeanPropertyRowMapper.newInstance(getClazz()));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /**
     * 取得当前泛型的实际class名
     *
     * @return
     */
    private Class<T> getClazz() {
        return ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    /**
     * 直接根据sql获取列表
     *
     * @param sql
     * @return
     */
    protected List<T> list(String sql) {
        List<T> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(getClazz()));
        return list;
    }

    /**
     * 根据sql和多参数获取列表
     *
     * @param sql
     * @param objects
     * @return
     */
    protected List<T> list(String sql, Object... objects) {
        List<T> list = jdbcTemplate.query(sql, objects, BeanPropertyRowMapper.newInstance(getClazz()));
        return list;
    }

    /**
     * 根据查询条件获取列表
     *
     * @param sql
     * @param s
     * @return
     */
    protected List<T> list(String sql, S s) {
        List<T> list = namedParameterJdbcTemplate.query(sql, new BeanPropertySqlParameterSource(s), BeanPropertyRowMapper.newInstance(getClazz()));
        return list;
    }

    /**
     * 查询总数，无参数
     *
     * @param sql
     * @return
     */
    protected int count(String sql) {
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * 查询总数，带参数
     *
     * @param sql
     * @return
     */
    protected int count(String sql, Object... objects) {
        return jdbcTemplate.queryForObject(sql, objects, Integer.class);
    }

    /**
     * 查询总数，带查询对象
     *
     * @param sql
     * @param s
     * @return
     */
    protected int count(String sql, S s) {
        return namedParameterJdbcTemplate.queryForObject(sql, new BeanPropertySqlParameterSource(s), Integer.class);
    }
    /**
     * 下拉框列表
     *
     * @param sql
     * @return
     */
    protected List<ComboboxVO> listCombobox(String sql) {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ComboboxVO.class));
    }

    /**
     * 下拉框列表
     *
     * @param sql
     * @return
     */
    protected List<ComboboxVO> listCombobox(String sql, Object... objects) {
        return jdbcTemplate.query(sql, objects, new BeanPropertyRowMapper<>(ComboboxVO.class));
    }
}
