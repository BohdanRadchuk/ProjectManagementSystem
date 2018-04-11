SELECT avg(salary)
FROM developers, project_developer, projects
WHERE developers.id_dev IN (
  SELECT DISTINCT project_developer.id_dev
  WHERE (project_developer.id_project = ( SELECT projects.id_project WHERE (cost = ( SELECT min(cost)
  FROM projects))))
);
        
      