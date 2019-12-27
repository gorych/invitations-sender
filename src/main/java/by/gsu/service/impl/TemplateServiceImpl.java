package by.gsu.service.impl;

import by.gsu.dao.TemplateDao;
import by.gsu.model.Template;
import by.gsu.service.TemplateService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private final TemplateDao templateDao;

    @Override
    public List<Template> getAll() {
        return templateDao.findAll();
    }

}
