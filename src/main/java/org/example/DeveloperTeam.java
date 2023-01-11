package org.example;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class DeveloperTeam {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private String teamName;
        private Developer teamLead;
        @ManyToMany
        private List<Developer> teamList;


        public DeveloperTeam(){
            super();
        }

        public DeveloperTeam(String teamName, Developer teamLead, List<Developer> teamList){
            super();
            this.teamName = teamName;
            this.teamLead = teamLead;
            this.teamList = teamList;
        }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getTeamName() {
                return teamName;
            }

            public void setTeamName(String teamName) {
                this.teamName = teamName;
            }

            public Developer getTeamLead() {
                return teamLead;
            }

            public void setTeamLead(Developer teamLead) {
                this.teamLead = teamLead;
            }

            public List<Developer> getTeamList(){
                return teamList;
            }

            public void setTeamList(List<Developer> teamList){
                this.teamList = teamList;
            }
}
