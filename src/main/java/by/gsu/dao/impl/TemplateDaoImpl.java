package by.gsu.dao.impl;

import by.gsu.dao.util.DbManager;
import by.gsu.dao.TemplateDao;
import by.gsu.model.Template;

import java.util.List;

import static by.gsu.domain.tables.Template.TEMPLATE;

public class TemplateDaoImpl implements TemplateDao {

    @Override
    public List<Template> findAll() {
        return DbManager.getDslContext()
                .select()
                .from(TEMPLATE)
                .fetch()
                .map(Template::new);
    }

}