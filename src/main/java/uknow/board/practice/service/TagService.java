package uknow.board.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uknow.board.practice.entity.PostTag;
import uknow.board.practice.entity.Tag;
import uknow.board.practice.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {

    private final TagRepository tagRepository;

    public List<String> tagList() {
        List<Tag> tags = tagRepository.findAll();
        List<String> tagList = new ArrayList<>();
        tags.forEach(tag -> {
            tagList.add(tag.getTagName());
        });
        return tagList;
    }

    @Transactional
    public List<Tag> tagArrangement(final List<String> tagList) {
        List<Tag> tags = new ArrayList<>();
        tagList.forEach(tagName -> {
            Tag tag = tagRepository.findByTagName(tagName)
                    .orElseGet(() -> tagRepository.save(Tag.from(tagName)));
            if (!tags.contains(tag)) tags.add(tag);
        });
        return tags;
    }

    public List<String> postTagsToTagNameList(List<PostTag> postTags) {
        List<String> tagList = new ArrayList<>();
        postTags.forEach(postTag -> {
            String tagName = postTag.getTag().getTagName();
            tagList.add(tagName);
        });
        return tagList;
    }
}
