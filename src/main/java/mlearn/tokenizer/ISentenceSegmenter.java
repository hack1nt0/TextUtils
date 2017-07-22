package mlearn.tokenizer;

import java.util.List;

/**
 * Created by dy on 15-9-23.
 */
public interface ISentenceSegmenter {
    List<Sentence> split(String text);
}
