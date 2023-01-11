package org.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperTeamRepository extends CrudRepository<DeveloperTeam,Long> {
}
