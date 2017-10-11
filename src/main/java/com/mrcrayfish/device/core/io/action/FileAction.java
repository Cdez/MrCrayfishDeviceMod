package com.mrcrayfish.device.core.io.action;

import com.mrcrayfish.device.api.io.File;
import com.mrcrayfish.device.api.io.Folder;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Author: MrCrayfish
 */
public class FileAction
{
    private Type type;
    private NBTTagCompound data;

    private FileAction(Type type, NBTTagCompound data)
    {
        this.type = type;
        this.data = data;
    }

    public NBTTagCompound toTag()
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("type", type.ordinal());
        tag.setTag("data", data);
        return tag;
    }

    public static FileAction fromTag(NBTTagCompound tag)
    {
        Type type = Type.values()[tag.getInteger("type")];
        NBTTagCompound data = tag.getCompoundTag("data");
        return new FileAction(type, data);
    }

    public Type getType()
    {
        return type;
    }

    public NBTTagCompound getData()
    {
        return data;
    }

    public enum Type
    {
        NEW, DELETE, RENAME, DATA;
    }

    public static class Factory
    {
        public static FileAction makeNew(Folder parent, File file, boolean override)
        {
            NBTTagCompound vars = new NBTTagCompound();
            vars.setString("directory", parent.getPath());
            vars.setString("file_name", file.getName());
            vars.setBoolean("override", override);
            vars.setTag("data", file.toTag());
            return new FileAction(FileAction.Type.NEW, vars);
        }

        public static FileAction makeDelete(File file)
        {
            NBTTagCompound vars = new NBTTagCompound();
            vars.setString("directory", file.getLocation());
            vars.setString("file_name", file.getName());
            return new FileAction(FileAction.Type.DELETE, vars);
        }

        public static FileAction makeRename(File file, String newFileName)
        {
            NBTTagCompound vars = new NBTTagCompound();
            vars.setString("directory", file.getLocation());
            vars.setString("file_name", file.getName());
            vars.setString("new_file_name", newFileName);
            return new FileAction(FileAction.Type.RENAME, vars);
        }

        public static FileAction makeData(File file, NBTTagCompound data)
        {
            NBTTagCompound vars = new NBTTagCompound();
            vars.setString("directory", file.getLocation());
            vars.setString("file_name", file.getName());
            vars.setTag("data", data);
            return new FileAction(FileAction.Type.DATA, vars);
        }
    }
}