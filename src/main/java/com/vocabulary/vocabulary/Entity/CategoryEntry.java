package com.vocabulary.vocabulary.Entity;

import com.vocabulary.vocabulary.Common.DefaultCheckable;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "category")
@ToString
public class CategoryEntry implements DefaultCheckable {

    @Id
    private ObjectId id;

    private String name;
    private String description;
    private boolean enabled = true;
    private boolean isDefaultValue;

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    public String getId() {
        return id != null ? id.toHexString() : null;
    }

    public void setId(String id) {
        this.id = id != null ? new ObjectId(id) : null;
    }

    @Override
    public boolean isDefaultValue() {
        return isDefaultValue;
    }

    @Override
    public String getName() {
        return name;
    }
}
