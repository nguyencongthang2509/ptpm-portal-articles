package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserCreateArticleRequest;
import com.articlesproject.core.user.model.request.UserMyArticleByStatusRequest;
import com.articlesproject.core.user.model.request.UserMyArticleRequest;
import com.articlesproject.core.user.model.request.UserUpdateArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.model.response.UserMyArticleResponse;
import com.articlesproject.core.user.repository.UserArticleHashtagRepository;
import com.articlesproject.core.user.repository.UserHistoryRepository;
import com.articlesproject.core.user.repository.UserMyArticleRepository;
import com.articlesproject.core.user.repository.UserRepository;
import com.articlesproject.core.user.service.UserMyArticleService;
import com.articlesproject.entity.Articles;
import com.articlesproject.entity.ArticlesHashtag;
import com.articlesproject.entity.History;
import com.articlesproject.infrastructure.constant.ArticleStatus;
import com.articlesproject.infrastructure.constant.Message;
import com.articlesproject.infrastructure.exception.rest.RestApiException;
import com.articlesproject.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserMyArticleServiceImpl implements UserMyArticleService {

    @Autowired
    private UserMyArticleRepository userMyArticleRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Autowired
    private UserArticleHashtagRepository articleHashtagRepository;

    private final FormUtils formUtils = new FormUtils();

    @Cacheable(value = "allMyArticle", key = "#userId + '_' + #request.page")
    @Override
    public PageableObject<UserMyArticleResponse> getAllMyArticle(UserMyArticleRequest request, String userId) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<UserMyArticleResponse> res = userMyArticleRepository.getAllMyArticle(pageable, userId, request);
        return new PageableObject<>(res);
    }

    @Cacheable(value = "allMyArticleByStatus", key = "#userId + '_' + #request.page + '_' + #request.status")
    @Override
    public PageableObject<UserMyArticleResponse> getAllMyArticleByStatus(UserMyArticleByStatusRequest request, String userId) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<UserMyArticleResponse> res = userMyArticleRepository.getAllMyArticleByStatus(pageable, userId, request);
        return new PageableObject<>(res);
    }

    @Override
    public Articles updateArticle(String id, UserUpdateArticleRequest request) throws IOException {
        Optional<Articles> articles = userMyArticleRepository.findById(id);
        if (articles.isPresent()) {
            articles.get().setTitle(request.getTitle());
            articles.get().setDescriptive(request.getDescriptive());
            if (request.getStatus() == 1) {
                articles.get().setStatus(ArticleStatus.BAN_NHAP);
            } else if (request.getStatus() == 2) {
                articles.get().setStatus(ArticleStatus.CHO_PHE_DUYET);
            } else if (request.getStatus() == 3) {
                articles.get().setStatus(ArticleStatus.DA_PHE_DUYET);
            } else if (request.getStatus() == 4) {
                articles.get().setStatus(ArticleStatus.DA_HUY);
            }
            String currentDirectory1 = System.getProperty("user.dir");
            String folderName = articles.get().getId();
            String folderPath = currentDirectory1 + "/articles-project/src/main/resources/templates/articles/" + folderName;
            File folder = new File(folderPath);
            File imageFile = new File(folderPath + "/image.png");
            if (folder.exists()) {
                imageFile.delete();
                String fileName = "toi-thanh-cong-roi.html";
                File dir = new File(folderPath);
                File actualFile = new File(dir, fileName);
                FileWriter fileWriter = new FileWriter(actualFile);
                fileWriter.write(request.getContent());
                fileWriter.close();
                String regex = "data:image/(png|jpeg|jpg);base64,([^\"]+)";
                Pattern pattern = Pattern.compile(regex);
                String html = new String(Files.readAllBytes(Paths.get(folderPath + "/toi-thanh-cong-roi.html")));
                Matcher matcher = pattern.matcher(html);
                String imageName = "image" + "." + "png";
                if (matcher.find()) {
                    String base64Data = matcher.group(2);
                    byte[] imageData = Base64.getDecoder().decode(base64Data);
                    Files.write(Paths.get(folderPath + "/" + imageName), imageData, StandardOpenOption.CREATE_NEW);
                } else {
                    String imageDefaultPath = currentDirectory1 + "/front_end/assets/images/blog.png";
                    byte[] imageData = null;
                    try {
                        Path path = Paths.get(imageDefaultPath);
                        imageData = Files.readAllBytes(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String newImagePath = folderPath + "/" + imageName;
                    try {
                        Path path = Paths.get(newImagePath);
                        Files.write(path, imageData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return userMyArticleRepository.save(articles.get());
    }

    @Override
    public UserArticleResponse getArticleById(String id, String userId) {
        Optional<UserArticleResponse> articles = userMyArticleRepository.findArticleById(id, userId);
        Optional<History> historyOptionalArticlesIdAndUsersId = userHistoryRepository.findHistoriesByArticlesIdAndUsersId(id, userId);
        if (!historyOptionalArticlesIdAndUsersId.isPresent()) {
            History history = new History();
            history.setArticlesId(id);
            history.setUsersId(userId);
            history.setCreateAt(new Date().getTime());
            userHistoryRepository.save(history);
            System.out.println("Chạy vào thêm ok");
        } else {
            System.out.println(historyOptionalArticlesIdAndUsersId.get());
            historyOptionalArticlesIdAndUsersId.get().setCreateAt(new Date().getTime());
            userHistoryRepository.save(historyOptionalArticlesIdAndUsersId.get());
            System.out.println("Chạy vào update ok");
        }

        if (!articles.isPresent()) {
            throw new RestApiException(Message.ERROR_UNKNOWN);
        }
        return articles.get();
    }

    @Override
    public Articles addArticle(UserCreateArticleRequest request, String userId) throws IOException {
        Articles ar = formUtils.convertToObject(Articles.class, request);
        ar.setUsersId(userId);
        if (request.getTitle() == null) {
            throw new RestApiException(Message.TITLE_IS_NOT_NULL);
        }
        ar.setStatus(ArticleStatus.CHO_PHE_DUYET);
        userMyArticleRepository.save(ar);
        String currentDirectory1 = System.getProperty("user.dir");
        String folderName = ar.getId();
        String folderPath = currentDirectory1 + "/articles-project/src/main/resources/templates/articles/" + folderName;
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
            String fileName = "toi-thanh-cong-roi.html";
            File dir = new File(folderPath);
            File actualFile = new File(dir, fileName);
            actualFile.getParentFile().mkdirs();
            actualFile.createNewFile();
            FileWriter fileWriter = new FileWriter(actualFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(request.getContent());
            bufferedWriter.close();
            String regex = "data:image/(png|jpeg|jpg);base64,([^\"]+)";
            Pattern pattern = Pattern.compile(regex);
            String html = new String(Files.readAllBytes(Paths.get(folderPath + "/toi-thanh-cong-roi.html")));
            Matcher matcher = pattern.matcher(html);
            String imageName = "image" + "." + "png";
            if (matcher.find()) {
                String base64Data = matcher.group(2);
                byte[] imageData = Base64.getDecoder().decode(base64Data);
                Files.write(Paths.get(folderPath + "/" + imageName), imageData, StandardOpenOption.CREATE_NEW);
            } else {
                String imageDefaultPath = currentDirectory1 + "/front_end/assets/images/blog.png";
                byte[] imageData = null;
                try {
                    Path path = Paths.get(imageDefaultPath);
                    imageData = Files.readAllBytes(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String newImagePath = folderPath + "/" + imageName;
                try {
                    Path path = Paths.get(newImagePath);
                    Files.write(path, imageData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return ar;
        }
        return null;
    }

    @Override
    public Articles addDraftArticle(UserCreateArticleRequest request, String userId) throws IOException {
        Articles ar = formUtils.convertToObject(Articles.class, request);
        ar.setUsersId(userId);
        if (request.getTitle() == null) {
            throw new RestApiException(Message.TITLE_IS_NOT_NULL);
        }
        ar.setStatus(ArticleStatus.BAN_NHAP);
        userMyArticleRepository.save(ar);
        String currentDirectory1 = System.getProperty("user.dir");
        String folderName = ar.getId();
        String folderPath = currentDirectory1 + "/articles-project/src/main/resources/templates/articles/" + folderName;
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
            String fileName = "toi-thanh-cong-roi.html";
            File dir = new File(folderPath);
            File actualFile = new File(dir, fileName);
            actualFile.getParentFile().mkdirs();
            actualFile.createNewFile();
            FileWriter fileWriter = new FileWriter(actualFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(request.getContent());
            bufferedWriter.close();
            String regex = "data:image/(png|jpeg|jpg);base64,([^\"]+)";
            Pattern pattern = Pattern.compile(regex);
            String html = new String(Files.readAllBytes(Paths.get(folderPath + "/toi-thanh-cong-roi.html")));
            Matcher matcher = pattern.matcher(html);
            String imageName = "image" + "." + "png";
            if (matcher.find()) {
                String base64Data = matcher.group(2);
                byte[] imageData = Base64.getDecoder().decode(base64Data);
                Files.write(Paths.get(folderPath + "/" + imageName), imageData, StandardOpenOption.CREATE_NEW);
            } else {
                String imageDefaultPath = currentDirectory1 + "/front_end/assets/images/blog.png";
                byte[] imageData = null;
                try {
                    Path path = Paths.get(imageDefaultPath);
                    imageData = Files.readAllBytes(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String newImagePath = folderPath + "/" + imageName;
                try {
                    Path path = Paths.get(newImagePath);
                    Files.write(path, imageData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return ar;
        }
        return null;
    }

    @Override
    public boolean deleteArticle(String id) {
        Optional<Articles> articles = userMyArticleRepository.findById(id);
        if (!articles.isPresent()) {
            throw new RestApiException(Message.ARTICLE_NOT_EXIT);
        }
        List<ArticlesHashtag> currentArticlesHashtags = articleHashtagRepository.findByArticlesId(id);

        if (articles.get().getStatus() == ArticleStatus.MOI_TAO ||
                articles.get().getStatus() == ArticleStatus.BAN_NHAP ||
                articles.get().getStatus() == ArticleStatus.CHO_PHE_DUYET ||
                articles.get().getStatus() == ArticleStatus.DA_HUY
        ) {
            userMyArticleRepository.deleteById(id);
            currentArticlesHashtags.stream()
                    .forEach(current -> articleHashtagRepository.delete(current));
            String currentDirectory1 = System.getProperty("user.dir");
            String folderName = articles.get().getId();
            String folderPath = currentDirectory1 + "/articles-project/src/main/resources/templates/articles/" + folderName;
            File folder = new File(folderPath);
            File[] contents = folder.listFiles();
            if (contents != null) {
                for (File file : contents) {
                    file.delete();
                }
            }
            folder.delete();
        } else if (articles.get().getStatus() == ArticleStatus.DA_PHE_DUYET) {
            articles.get().setStatus(ArticleStatus.DA_XOA);
            userMyArticleRepository.save(articles.get());
        }
        return true;
    }

    @Override
    public UserArticleResponse getArticleUpdateById(String id, String idUser) {
        Optional<UserArticleResponse> articles = userMyArticleRepository.findArticleUpdateById(id, idUser);
        if (!articles.isPresent()) {
            throw new RestApiException(Message.ERROR_UNKNOWN);
        }
        return articles.get();
    }

    @Override
    public Articles updateArticleToCensor(String id, UserUpdateArticleRequest request) throws IOException {
        Optional<Articles> articles = userMyArticleRepository.findById(id);
        if (articles.isPresent()) {
            articles.get().setTitle(request.getTitle());
            articles.get().setDescriptive(request.getDescriptive());
            articles.get().setStatus(ArticleStatus.CHO_PHE_DUYET);
            String currentDirectory1 = System.getProperty("user.dir");
            String folderName = articles.get().getId();
            String folderPath = currentDirectory1 + "/articles-project/src/main/resources/templates/articles/" + folderName;
            File folder = new File(folderPath);
            File imageFile = new File(folderPath + "/image.png");
            if (folder.exists()) {
                imageFile.delete();
                String fileName = "toi-thanh-cong-roi.html";
                File dir = new File(folderPath);
                File actualFile = new File(dir, fileName);
                FileWriter fileWriter = new FileWriter(actualFile);
                fileWriter.write(request.getContent());
                fileWriter.close();
                String regex = "data:image/(png|jpeg|jpg);base64,([^\"]+)";
                Pattern pattern = Pattern.compile(regex);
                String html = new String(Files.readAllBytes(Paths.get(folderPath + "/toi-thanh-cong-roi.html")));
                Matcher matcher = pattern.matcher(html);
                String imageName = "image" + "." + "png";
                if (matcher.find()) {
                    String base64Data = matcher.group(2);
                    byte[] imageData = Base64.getDecoder().decode(base64Data);
                    Files.write(Paths.get(folderPath + "/" + imageName), imageData, StandardOpenOption.CREATE_NEW);
                } else {
                    String imageDefaultPath = currentDirectory1 + "/front_end/assets/images/blog.png";
                    byte[] imageData = null;
                    try {
                        Path path = Paths.get(imageDefaultPath);
                        imageData = Files.readAllBytes(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String newImagePath = folderPath + "/" + imageName;
                    try {
                        Path path = Paths.get(newImagePath);
                        Files.write(path, imageData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return userMyArticleRepository.save(articles.get());
    }
}
