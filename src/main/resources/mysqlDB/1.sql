SELECT sum(salary)
FROM developers
WHERE id_dev IN (SELECT developer_projects.id_dev
                 FROM developer_projects
                 WHERE developer_projects.id_project = 5);