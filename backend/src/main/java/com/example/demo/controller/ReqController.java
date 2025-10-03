package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Requirement;
import com.example.demo.repository.ReqRepository;

import lombok.RequiredArgsConstructor;

// 标明这是 REST API 控制器，返回 JSON 数据
@RestController

// 定义所有 API 的基础路径
@RequestMapping("/api/requirements")

// Lombok 注解，自动生成构造函数来注入依赖
@RequiredArgsConstructor

public class ReqController {
    private final ReqRepository repo;

    // 获取所有需求
    @GetMapping
    public List<Requirement> list() {
        return repo.findAll();
    }

    // 创建新需求
    @PostMapping
    public Requirement create(@RequestBody Requirement r) {
        return repo.save(r);
    }

    // 获取单个需求
    @GetMapping("/{id}")
    public ResponseEntity<Requirement> get(@PathVariable Long id) {
        return repo.findById(id)  // 调用 Repository 查询数据库，返回 Optional<Requirement>
                .map(ResponseEntity::ok)  // 如果 Optional 有值，就用 ResponseEntity.ok() 包装它（HTTP 200）
                .orElse(ResponseEntity.notFound().build());  // 如果 Optional 为空，返回 ResponseEntity.notFound()（HTTP 404）
    }

    // 更新需求
    @PutMapping("/{id}")
    public ResponseEntity<Requirement> update(@PathVariable Long id, @RequestBody Requirement r) {
        return repo.findById(id).map(existing -> {
            // existing: 数据库中找到的旧需求
            // r: 前端传来的新需求数据
            
            existing.setTitle(r.getTitle());     // 更新标题
            existing.setDescription(r.getDescription());  // 更新描述
            existing.setStatus(r.getStatus());  // 更新状态
            
            repo.save(existing);   // 保存到数据库
            return ResponseEntity.ok(existing);  // 返回200 + 更新后的数据
        }).orElse(ResponseEntity.notFound().build());
    }

    // 删除需求
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
