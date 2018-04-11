SELECT
  ProjectName,
  cost
FROM projects
WHERE (cost = (SELECT min(cost)
               FROM projects));

