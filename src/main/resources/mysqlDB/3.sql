SELECT sum(salary)
FROM developers, developer_skill
WHERE developers.id_dev IN (
  SELECT DISTINCT developer_skill.id_dev
  WHERE (developer_skill.id_skill BETWEEN 1 AND 3 ));