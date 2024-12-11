package com.example.chatapp.cht_Controller;

import com.example.chatapp.cht_Entity.Group;
import com.example.chatapp.cht_Service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    // Create a new group
    @PostMapping("/create")
    public Group createGroup(@RequestBody Group group) {
        return groupService.createGroup(group.getName(), group.getMembers());
    }

    // Get details of a group by ID
    @GetMapping("/{groupId}")
    public Group getGroup(@PathVariable String groupId) {
        return groupService.getGroupById(groupId);
    }

    // Add a member to a group
    @PutMapping("/{groupId}/addMember")
    public Group addMember(@PathVariable String groupId, @RequestParam String member) {
        return groupService.addMemberToGroup(groupId, member);
    }

    // Get all groups
    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }
}
