package com.example.chatapp.cht_Service;

import com.example.chatapp.cht_Entity.Group;
import com.example.chatapp.cht_Repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    // Create a new group
    public Group createGroup(String name, List<String> members) {
        Group group = new Group(name, members);
        return groupRepository.save(group);
    }

    // Fetch a group by its ID
    public Group getGroupById(String groupId) {
        return groupRepository.findById(groupId).orElse(null);
    }

    // Add a member to a group
    public Group addMemberToGroup(String groupId, String member) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            group.getMembers().add(member);
            return groupRepository.save(group);
        }
        return null;
    }

    // Get all groups
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}
