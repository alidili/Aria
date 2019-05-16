/*
 * Copyright (C) 2016 AriaLyy(https://github.com/AriaLyy/Aria)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.arialyy.aria.core.inf;

import com.arialyy.aria.core.common.ftp.FtpTaskDelegate;
import com.arialyy.aria.core.common.http.HttpTaskDelegate;
import com.arialyy.aria.core.config.BaseTaskConfig;
import com.arialyy.aria.core.config.DGroupConfig;
import com.arialyy.aria.core.config.DownloadConfig;
import com.arialyy.aria.core.config.UploadConfig;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.download.DownloadGroupEntity;
import com.arialyy.aria.core.upload.UploadEntity;

/**
 * Created by lyy on 2017/2/23. 所有任务实体的父类
 */
public abstract class AbsTaskWrapper<ENTITY extends AbsEntity>
    implements ITaskWrapper<ENTITY> {

  private HttpTaskDelegate httpTaskDelegate;
  private FtpTaskDelegate ftpTaskDelegate;

  /**
   * 刷新信息 {@code true} 重新刷新下载信息
   */
  private boolean refreshInfo = false;

  /**
   * 是否是新任务，{@code true} 新任务
   */
  private boolean isNewTask = false;

  /**
   * 请求类型 {@link AbsTaskWrapper#D_HTTP}、{@link AbsTaskWrapper#D_FTP}、{@link
   * AbsTaskWrapper#D_FTP_DIR}。。。
   */
  private int requestType = D_HTTP;

  /**
   * 删除任务时，是否删除已下载完成的文件 未完成的任务，不管true还是false，都会删除文件 {@code true}  删除任务数据库记录，并且删除已经下载完成的文件 {@code
   * false} 如果任务已经完成，只删除任务数据库记录
   */
  private boolean removeFile = false;

  /**
   * 是否支持断点, {@code true} 为支持断点
   */
  private boolean isSupportBP = true;

  /**
   * 状态码
   */
  private int code;

  /**
   * {@link DownloadEntity} or {@link UploadEntity} or {@link DownloadGroupEntity}
   */
  private ENTITY entity;

  public AbsTaskWrapper(ENTITY entity) {
    this.entity = entity;
  }

  /**
   * 任务识别标志 {@link AbsEntity#getKey()}
   */
  public abstract String getKey();

  /**
   * 任务配置
   *
   * @return {@link DownloadConfig}、{@link UploadConfig}、{@link DGroupConfig}
   */
  public abstract BaseTaskConfig getConfig();

  @Override public ENTITY getEntity() {
    return entity;
  }

  /**
   * 设置或获取HTTP任务相关参数
   */
  public HttpTaskDelegate asHttp() {
    if (httpTaskDelegate == null) {
      httpTaskDelegate = new HttpTaskDelegate();
    }
    return httpTaskDelegate;
  }

  /**
   * 设置或获取FTP任务相关参数
   */
  public FtpTaskDelegate asFtp() {
    if (ftpTaskDelegate == null) {
      ftpTaskDelegate = new FtpTaskDelegate();
    }
    return ftpTaskDelegate;
  }

  /**
   * 获取任务下载状态
   *
   * @return {@link IEntity}
   */
  public int getState() {
    return getEntity().getState();
  }

  public boolean isRefreshInfo() {
    return refreshInfo;
  }

  public void setRefreshInfo(boolean refreshInfo) {
    this.refreshInfo = refreshInfo;
  }

  public boolean isNewTask() {
    return isNewTask;
  }

  public void setNewTask(boolean newTask) {
    isNewTask = newTask;
  }

  public void setState(int state) {
    entity.setState(state);
  }

  public int getRequestType() {
    return requestType;
  }

  public void setRequestType(int requestType) {
    this.requestType = requestType;
  }

  public boolean isRemoveFile() {
    return removeFile;
  }

  public void setRemoveFile(boolean removeFile) {
    this.removeFile = removeFile;
  }

  public boolean isSupportBP() {
    return isSupportBP;
  }

  public void setSupportBP(boolean supportBP) {
    isSupportBP = supportBP;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
