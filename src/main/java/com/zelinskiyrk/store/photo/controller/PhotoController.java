package com.zelinskiyrk.store.photo.controller;

import com.zelinskiyrk.store.base.api.response.OkResponse;
import com.zelinskiyrk.store.photo.api.response.PhotoResponse;
import com.zelinskiyrk.store.photo.exception.PhotoExistException;
import com.zelinskiyrk.store.photo.mapping.PhotoMapping;
import com.zelinskiyrk.store.photo.model.PhotoDoc;
import com.zelinskiyrk.store.photo.routes.PhotoApiRoutes;
import com.zelinskiyrk.store.photo.service.PhotoApiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Api(value = "Photo API")
public class PhotoController {
    private final PhotoApiService photoApiService;

    @PostMapping(PhotoApiRoutes.ROOT)
    @ApiOperation(value = "Create", notes = "Use this when you need upload new photo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Photo already exist")
    })
    public @ResponseBody
    OkResponse<PhotoResponse> addPhoto(
            @RequestParam MultipartFile file
    ) throws IOException, PhotoExistException {
        return OkResponse.of(PhotoMapping.getInstance().getResponse().convert(photoApiService.addPhoto(file)));
    }

    @GetMapping(PhotoApiRoutes.DOWNLOAD)
    @ApiOperation(value = "Download photo by ID", notes = "Use this when you need download photo")
    public void byId(
            @ApiParam(value = "Photo ID") @PathVariable ObjectId id, HttpServletResponse response
    ) throws ChangeSetPersister.NotFoundException, IOException {
        PhotoDoc photoDoc = photoApiService.findById(id).orElseThrow();
        response.addHeader("Content-Type",  photoDoc.getContentType());
        response.addHeader("Content-Disposition",  ": inline; filename\""+photoDoc.getPhotoName()+"\"");
        FileCopyUtils.copy(photoApiService.downloadById(id), response.getOutputStream());
    }
}
