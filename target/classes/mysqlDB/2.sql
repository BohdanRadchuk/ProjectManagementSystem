select  avg (salary)
from developers, project_developer, projects 
	where  developers.id_dev IN (
	select DISTINCT project_developer.id_dev
		where (project_developer.id_project = (select projects.id_project where (cost = (select min(cost)
				from projects))))
);
        
      