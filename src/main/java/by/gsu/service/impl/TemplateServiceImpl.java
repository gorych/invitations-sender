package by.gsu.service.impl;

import by.gsu.dao.TemplateDao;
import by.gsu.model.Template;
import by.gsu.service.TemplateService;

import java.util.List;

public class TemplateServiceImpl implements TemplateService {

    private TemplateDao templateDao;

    public TemplateServiceImpl(TemplateDao templateDao) {
        this.templateDao = templateDao;
    }

    @Override
    public List<Template> getAll() {
        return templateDao.findAll();
    }

}
