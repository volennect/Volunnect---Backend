package com.example.chatapp.cht_Service;

import com.example.chatapp.cht_Entity.Group;
import com.example.chatapp.cht_Repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
