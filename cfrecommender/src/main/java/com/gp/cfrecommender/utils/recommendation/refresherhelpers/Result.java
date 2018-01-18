/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.utils.recommendation.refresherhelpers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author adel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    private long contestId, creationTimeSeconds, id, memoryConsumedBytes,
            passedTestCount, relativeTimeSeconds, timeConsumedMillis;
    private Problem problem;
    private String programmingLanguage, testset, verdict;
    //private Author author;

    public long getContestId() {
        return contestId;
    }

    public void setContestId(long contestId) {
        this.contestId = contestId;
    }

    public long getCreationTimeSeconds() {
        return creationTimeSeconds;
    }

    public void setCreationTimeSeconds(long creationTimeSeconds) {
        this.creationTimeSeconds = creationTimeSeconds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMemoryConsumedBytes() {
        return memoryConsumedBytes;
    }

    public void setMemoryConsumedBytes(long memoryConsumedBytes) {
        this.memoryConsumedBytes = memoryConsumedBytes;
    }

    public long getPassedTestCount() {
        return passedTestCount;
    }

    public void setPassedTestCount(long passedTestCount) {
        this.passedTestCount = passedTestCount;
    }

    public long getRelativeTimeSeconds() {
        return relativeTimeSeconds;
    }

    public void setRelativeTimeSeconds(long relativeTimeSeconds) {
        this.relativeTimeSeconds = relativeTimeSeconds;
    }

    public long getTimeConsumedMillis() {
        return timeConsumedMillis;
    }

    public void setTimeConsumedMillis(long timeConsumedMillis) {
        this.timeConsumedMillis = timeConsumedMillis;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem prob) {
        this.problem = prob;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getTestset() {
        return testset;
    }

    public void setTestset(String testset) {
        this.testset = testset;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

//    public Author getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(Author auth) {
//        this.author = auth;
//    }

    public static class Author {

        long contestId, room, startTimeSeconds;
        String[] members;
        String participantType, teamName;
        Boolean ghost;

        public long getContestId() {
            return contestId;
        }

        public void setContestId(long contestId) {
            this.contestId = contestId;
        }

        public long getRoom() {
            return room;
        }

        public void setRoom(long room) {
            this.room = room;
        }

        public long getStartTimeSeconds() {
            return startTimeSeconds;
        }

        public void setStartTimeSeconds(long startTimeSeconds) {
            this.startTimeSeconds = startTimeSeconds;
        }

        public String[] getMembers() {
            return members;
        }

        public void setMembers(String[] members) {
            this.members = members;
        }

        public String getParticipantType() {
            return participantType;
        }

        public void setParticipantType(String participantType) {
            this.participantType = participantType;
        }

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public Boolean getGhost() {
            return ghost;
        }

        public void setGhost(Boolean ghost) {
            this.ghost = ghost;
        }

    }

}
