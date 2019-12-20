package com.ltj.gateway.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ltj.gateway.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author: Liu Tian Jun
 * @Date: 2019-12-12 11:51
 * @describe： 通用 增删改查 控制器
 * @version: 1.0
 */

@Slf4j
public class BaseController<T, S extends IService<T>> {

    @Autowired(required = false)
    protected S baseService;

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") Serializable id) {
        log.info("get ID : {}", id);
        return Result.success(baseService.getById(id));
    }

    @GetMapping("/all")
    public Result findAll() {
        return Result.success(baseService.list());
    }

    @GetMapping("/page")
    public Result page(Page<T> page) {
        return Result.success(baseService.page(page));
    }

    @PostMapping()
    public Result save(@RequestBody T entity) {
        log.info("save :  {}", entity);
        return Result.success(baseService.save(entity)?"添加成功":"添加失败");
    }

    @PostMapping("/saveBatch")
    public Result saveBatch(@RequestBody Collection<T> entityList) {
        log.info("saveBatch :  {}", entityList);
        return Result.success(baseService.saveBatch(entityList)?"批量添加成功":"批量添加失败");
    }

    @PutMapping()
    public Result update(@RequestBody T entity) {
        log.info("update:  {}", entity);
        return Result.success(baseService.updateById(entity)?"修改成功":"修改失败");
    }

    @PutMapping("/updateBatch")
    public Result updateBatch(@RequestBody Collection<T> entityList) {
        log.info("updateBatch:  {}", entityList);
        return Result.success(baseService.updateBatchById(entityList)?"批量修改成功":"批量修改失败");
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody T entity) {
        log.info("saveOrUpdate :  {}", entity);
        return Result.success(baseService.saveOrUpdate(entity)?"操作成功":"操作失败");
    }

    @PostMapping("/saveOrUpdateBatch")
    public Result saveOrUpdateBatch(@RequestBody Collection<T> entityList) {
        log.info("saveOrUpdateBatch :  {}", entityList);
        return Result.success(baseService.saveOrUpdateBatch(entityList)?"批量操作成功":"批量操作失败");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Serializable id) {
        log.info("delete:  {}", id);
        return Result.success(baseService.removeById(id)?"删除成功":"删除失败");
    }

    @DeleteMapping("/deleteByIds")
    public Result deleteByIds(@RequestBody Collection<? extends Serializable> idList) {
        log.info("delete:  {}", idList);
        return Result.success(baseService.removeByIds(idList)?"批量删除成功":"批量删除失败");
    }
}