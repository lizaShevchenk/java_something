package javaTasks.tasks.library.pagination;

import javaTasks.tasks.library.author.Author;
import javaTasks.tasks.library.controller.View;
import javaTasks.tasks.library.storage.Repository;

import java.util.List;


public class Pagination {

    public static int printEntities(int pageIndex, Repository<Author> authorList, int numberOfAuthorsPerPage, View view) {
        List<Author> repository;
        if (pageIndex <= 0) {
            repository = List.copyOf(authorList.get().subList(0, numberOfAuthorsPerPage));
            view.write("1 страница:\n" + repository.toString());
            view.write("You have reached the first page. Please enter other value:");
            return 1;
        }
        double pagesCount = (double) authorList.get().size() / numberOfAuthorsPerPage;
        try {
            if (pagesCount < 1) {
                view.write("Это весь список авторов. Пагинация недоступна. Author list:\n" + authorList);
                return 1;
            }
            if (pageIndex > pagesCount) {
                pageIndex = (int) Math.ceil(pagesCount);
                repository = List.copyOf(authorList.get().subList((pageIndex - 1) * numberOfAuthorsPerPage, authorList.get().size()));
                view.write("Последняя страница хранилища:\n" + repository.toString());
                return pageIndex;
            }
            if (pageIndex < pagesCount) {
                repository = List.copyOf(authorList.get().subList((pageIndex - 1) * numberOfAuthorsPerPage, pageIndex * numberOfAuthorsPerPage));
                view.write(pageIndex + " страница:\n" + repository.toString());
                return pageIndex;
            }
        } catch (Exception e) {
            view.write("Error : pagination printMethodError. Please investigate.");
        }
        view.write("Need to investigate this error.");
        return pageIndex;
    }
}
