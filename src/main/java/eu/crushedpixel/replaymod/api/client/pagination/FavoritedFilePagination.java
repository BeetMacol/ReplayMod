package eu.crushedpixel.replaymod.api.client.pagination;

import eu.crushedpixel.replaymod.ReplayMod;
import eu.crushedpixel.replaymod.api.client.holders.FileInfo;
import eu.crushedpixel.replaymod.online.authentication.AuthenticationHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FavoritedFilePagination implements Pagination {

    private int page;
    private HashMap<Integer, FileInfo> files = new HashMap<Integer, FileInfo>();

    public FavoritedFilePagination() {
        this.page = -1;
    }

    @Override
    public List<FileInfo> getFiles() {
        return new ArrayList<FileInfo>(files.values());
    }

    @Override
    public int getLoadedPages() {
        return page;
    }

    @Override
    public boolean fetchPage() {
        page++;

        try {
            int[] f = ReplayMod.apiClient.getFavorites(AuthenticationHandler.getKey());
            List<Integer> toAdd = new ArrayList<Integer>();
            for(int i : f) {
                if(!files.containsKey(i)) {
                    toAdd.add(i);
                    if(toAdd.size() >= Pagination.PAGE_SIZE) break;
                }
            }

            files.keySet().retainAll(Arrays.asList(f));

            FileInfo[] fis = ReplayMod.apiClient.getFileInfo(toAdd);
            if(fis.length < 1) {
                page--;
                return false;
            }

            for(FileInfo info : fis) {
                files.put(info.getId(), info);
            }

            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        page--;
        return false;
    }
}
