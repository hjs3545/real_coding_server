package com.cnu.real_coding_server.controller;

import com.cnu.real_coding_server.entity.Project;
import com.cnu.real_coding_server.model.request.ProjectRequest;
import com.cnu.real_coding_server.repository.ProjectRepository;
import com.cnu.real_coding_server.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Projects")
public class ProjectController {
    private final ProjectService ProjectService;
    private final ProjectRepository ProjectRepository;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectRequest ProjectRequest){
        return ResponseEntity.ok( ProjectService.createProject(ProjectRequest));
    }

    @GetMapping
    public ResponseEntity<List<Project>> getProjects(){
        return ResponseEntity.ok(ProjectService.getProjects());
    }

    @GetMapping("/{ProjectId}")
    public ResponseEntity<Project> getProject(@PathVariable Integer ProjectId){
        return ResponseEntity.ok(ProjectService.getProject(ProjectId));
    }

    @PutMapping("/{ProjectId}")
    public ResponseEntity<Project> updateProject(@RequestBody ProjectRequest ProjectRequest, @PathVariable Integer ProjectId) {
        return ResponseEntity.ok(ProjectService.updateProject(ProjectRequest, ProjectId).orElse(null));
    }

    @DeleteMapping("/{ProjectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable("ProjectId") Integer ProjectId) {
        ProjectService.deleteProject(ProjectId);
        return ResponseEntity.noContent().build();
    }
}