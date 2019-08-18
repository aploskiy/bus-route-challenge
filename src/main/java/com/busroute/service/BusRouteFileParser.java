package com.busroute.service;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BusRouteFileParser {

  private static final Logger LOGGER = LoggerFactory.getLogger(BusRouteFileParser.class);
  public static final int ROUTE_FILE_PATH_INDEX = 0;

  public static List<Set<Integer>> parse(ApplicationArguments arguments) {
    checkPath(arguments.getSourceArgs());
    try {
      Path path = Paths.get(arguments.getSourceArgs()[ROUTE_FILE_PATH_INDEX]);
      return Files.lines(path)
          .skip(1)// skip rout count
          .filter(StringUtils::isNoneBlank)
          .map(BusRouteFileParser::parseLine)
          .collect(Collectors.toList());
    } catch (IOException e) {
      LOGGER.error(e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }

  private static Set<Integer> parseLine(String line) {
    return Stream.of(line.split(StringUtils.SPACE)).skip(1).map(Integer::valueOf).collect(Collectors.toSet());
  }

  private static void checkPath(String[] args) {
    checkFilePathArgExist(args);
    validateFile(args[ROUTE_FILE_PATH_INDEX]);
  }

  private static void checkFilePathArgExist(String[] args) {
    if (ArrayUtils.isNotEmpty(args)) {
      return;
    }
    throw new IllegalArgumentException("Route file path does not set");
  }

  private static void validateFile(String path) {
    Path routeFilepath = Paths.get(path);
    checkFileExist(routeFilepath);
    checkIsReadable(routeFilepath);
  }

  private static void checkFileExist(Path routeFilepath) {
    if (Files.exists(routeFilepath) && !Files.isDirectory(routeFilepath)) {
      return;
    }
    throw new IllegalArgumentException("Route file does not exist");
  }

  private static void checkIsReadable(Path routeFilepath) {
    if (Files.isReadable(routeFilepath)) {
      return;
    }
    throw new IllegalArgumentException("Route file does not have access to read");
  }

}
