# -*- coding:utf-8*-
# 利用PyPDF2模块合并同一文件夹下的所有PDF文件
# 只需修改存放PDF文件的文件夹变量：file_dir 和 输出文件名变量: outfile

import os
from PyPDF2 import PdfFileReader, PdfFileWriter
import time
import sys

# 使用os模块的walk函数，搜索出指定目录下的全部PDF文件
# 获取同一目录下的所有PDF文件的绝对路径
def getFileName(file_path):
    dir_list = os.listdir(file_path)
    if not dir_list:
        return
    else:
        # 注意，这里使用lambda表达式，将文件按照最后修改时间顺序升序排列
        # os.path.getmtime() 函数是获取文件最后修改时间
        # os.path.getctime() 函数是获取文件最后创建时间
        dir_list = sorted(dir_list,key=lambda x: os.path.getctime(os.path.join(file_path, x)))
        # print(dir_list)
        return dir_list

# 合并同一目录下的所有PDF文件
def MergePDFto(filepath, outfile):

    output = PdfFileWriter()
    outputPages = 0
    pdf_fileName = getFileName(filepath)

    if pdf_fileName:
        pdf_fileName.sort()
        for pdf_file in pdf_fileName:
            if str(pdf_file).endswith(".pdf"):
                pdf_path = filepath + "/" + pdf_file;
                print("路径：%s" % pdf_path)

                # 读取源PDF文件
                input = PdfFileReader(open(pdf_path, "rb"))

                # 获得源PDF文件中页面总数
                pageCount = input.getNumPages()
                outputPages += pageCount
                print("页数：%d"%pageCount)

                # 分别将page添加到输出output中
                for iPage in range(pageCount):
                    output.addPage(input.getPage(iPage))
                output.addBookmark(title = pdf_file[:-4], pagenum = outputPages - pageCount)

        print("合并后的总页数:%d."%outputPages)
        # 写入到目标PDF文件
        outputStream = open(outfile, "wb")
        output.write(outputStream)
        outputStream.close()
        print("PDF文件合并完成！")

    else:
        print("没有可以合并的PDF文件！")

# 主函数
time1 = time.time()
file_dir = sys.argv[1] # 存放PDF的原文件夹
outfile = sys.argv[2]+".pdf" # 输出的PDF文件的名称
print(file_dir+"/"+outfile)
MergePDFto(file_dir, outfile)
time2 = time.time()
print('总共耗时：%s s.' %(time2 - time1))
