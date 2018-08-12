package com.eshaghi.poc.spring.kafka.producer.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class Event {

    @JsonProperty("id")
    private String id;

    @JsonProperty("timestamp")
    private Instant timestamp;

    @JsonProperty("params")
    private Map<String, Object> params;

    public String getId() {
        return id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Optional<Object> getParam(String key) {
        return Optional.ofNullable(params.get(key));
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", params=" + params +
                '}';
    }

    private Event(Builder builder) {
        this.id = builder.id;
        this.timestamp = builder.timestamp;
        this.params = builder.params;
    }

    public static Event.Builder newBuilder() {
        return new Event.Builder();
    }

    public static final class Builder {

        private String id;
        private Instant timestamp;
        private Map<String, Object> params;

        private Builder() {
            params = new HashMap<>();
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withParam(String key, Object value) {
            this.params.put(key, value);
            return this;
        }

        public Builder withParams(Map<String, Object> params) {
            this.params.putAll(params);
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}
