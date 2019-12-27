package by.gsu.dao;

import by.gsu.model.Template;

import java.util.List;
import java.util.Optional;

import static by.gsu.domain.tables.Template.TEMPLATE;

public class TemplateDaoImpl implements TemplateDao {

    @Override
    public Optional<Template> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Template> findAll() {
        return DbManager.getDslContext()
                .select()
                .from(TEMPLATE)
                .fetch()
                .map(Template::new);
    }

    @Override
    public void save(Template template) {

    }

    @Override
    public void update(Template template) {

    }

    @Override
    public void delete(Template template) {

    }

}