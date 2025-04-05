package com.funshine.yetusote.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "groups")
public class Group {
    @Id
    private String groupId;
    private String groupName;
    private List<Member> members;
    private String email;
    private double totalContribution;
    private String password;
}
