select  sum(salary)
from developers, developer_skill 
	where  developers.id_dev IN (
	select DISTINCT developer_skill.id_dev
		where (developer_skill.id_skill between 1 and 3 ));