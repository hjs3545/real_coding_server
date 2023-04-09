package com.cnu.real_coding_server.service;

import com.cnu.real_coding_server.entity.Project;
import com.cnu.real_coding_server.model.request.ProjectRequest;
import com.cnu.real_coding_server.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository ProjectRepository;
    public Project createProject(ProjectRequest ProjectRequest){
        return ProjectRepository.save(ProjectRequest.toEntity());
    }
    public List<Project> getProjects(){
        return ProjectRepository.findAll();
    }

    public Project getProject(Integer ProjectId) {
        return ProjectRepository.findById(ProjectId).get();
    }

    public Optional<Project> updateProject(@RequestBody ProjectRequest ProjectRequest, @PathVariable Integer ProjectId) {
        return ProjectRepository.findById(ProjectId)
                .map(Project -> {
                    Project.setTitle(ProjectRequest.getTitle());
                    Project.setSummary(ProjectRequest.getSummary());
                    Project.setDescription(ProjectRequest.getDescription());
                    Project.setStartDate(ProjectRequest.getStartDate());
                    Project.setEndDate(ProjectRequest.getEndDate());
                    Project.setIsInProgress(ProjectRequest.getIsInProgress());
                    return ProjectRepository.save(Project);
                });
    }

    public void deleteProject(Integer ProjectId) {
        ProjectRepository.findById(ProjectId).ifPresent(ProjectRepository::delete);
    }
}